package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


import hry.util.properties.PropertiesUtils;
import org.springframework.util.StringUtils;

/**
 * ExcleUtil.java
 * 
 * @author denghf 2018年5月2日 上午11:15:01
 */
public class ExcelUtil {
	private static Logger logger = Logger.getLogger(ExcelUtil.class);
	public static void jdbcex(boolean isClose, String path, String filename, String sqlc, String sqls, String columsName,
			int size) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			IOException, InterruptedException {

		String xlsFile = path + "/" + filename + ".xlsx"; // 输出文件
		// 内存中只创建100个对象，写临时文件，当超过100条，就将内存中不用的对象释放。
		Workbook wb = new SXSSFWorkbook(100); // 关键语句
		Sheet sheet = null; // 工作表对象
		Row nRow = null; // 行对象
		Cell nCell = null; // 列对象
		
		InputStream insjdbc = ExcelUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		
		Properties jdbc = new Properties();
        try {
            jdbc.load(insjdbc);
        } catch (IOException e) {
            e.printStackTrace();
        }

		// 使用jdbc链接数据库
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String url = jdbc.getProperty("jdbc.url");
		String user = jdbc.getProperty("jdbc.username");
		String password = jdbc.getProperty("jdbc.password");
		// 获取数据库连接
		Connection conn = DriverManager.getConnection(url, user, password);
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		// 先count(*),分页查询,避免 Java heap space
		ResultSet eCount = stmt.executeQuery(sqlc);
		int sqlcount = 0;
		if (eCount.next()) {
			sqlcount = eCount.getInt(1);
			logger.error(sqlcount);
		}

		eCount.close();

		int count = 0;
		if (sqlcount % size > 0) {
			count = sqlcount / size + 1;
		}
		logger.error(count);
		//如果查询总数为0则创建一个空的excel文件
		if (count == 0) {
			// 建立新的sheet对象
			sheet = wb.createSheet("第 1 个工作簿");
			// 动态指定当前的工作表
			sheet = wb.getSheetAt(0);
			// 新建第一行对象
			nRow = sheet.createRow(0);
			if (!StringUtils.isEmpty(columsName)) {
				String[] split = columsName.split(",");
				for (int k = 0; k < split.length; k++) {
					nCell = nRow.createCell(k);
					nCell.setCellValue(split[k]);
				}
			}
		}

		long startTime = 0l;
		for (int i = 0; i < count; i++) {
			ResultSet rs = stmt.executeQuery(sqls + " limit " + i * size + "," + size);

			ResultSetMetaData rsmd = rs.getMetaData();
			startTime = System.currentTimeMillis(); // 开始时间
			logger.error("---第" + (i+1) + "张工作簿开始---");
			logger.error("start: " + startTime);

			int rowNo = 0; // 总行号
			int pageRowNo = 1; // 页行号

			while (rs.next()) {
				// 打印size条后切换到下个工作表，可根据需要自行拓展，2百万，3百万...数据一样操作，只要不超过1048576就可以
				// 由于前面是分页查询，所以这个if其实可以不需要了
				if (rowNo % size == 0) {
					// sheet = wb.createSheet("我的第" + (rowNo / size) +
					// "个工作簿");// 建立新的sheet对象
					sheet = wb.createSheet("第" + (i+1) + "个工作簿");
					// sheet = wb.getSheetAt(rowNo / size); // 动态指定当前的工作表
					sheet = wb.getSheetAt(i);
					pageRowNo = 1; // 每当新建了工作表就将当前工作表的行号重置为1

					nRow = sheet.createRow(0); // 新建第一行对象

					if (!StringUtils.isEmpty(columsName)) {
						String[] split = columsName.split(",");
						for (int k = 0; k < split.length; k++) {
							nCell = nRow.createCell(k);
							nCell.setCellValue(split[k]);
						}
					}
				}
				rowNo++;
				nRow = sheet.createRow(pageRowNo++); // 新建行对象

				// 打印每行，每行有n列数据 rsmd.getColumnCount()==n --- 列属性的个数
				for (int j = 0; j < rsmd.getColumnCount(); j++) {
					nCell = nRow.createCell(j);
					nCell.setCellValue(rs.getString(j + 1));
				}

				if (rowNo % 10000 == 0) {
					// logger.error("row no: " + rowNo);
				}
			}

			long finishedTime = System.currentTimeMillis(); // 处理完成时间
			logger.error("end: " + (finishedTime - startTime) / 1000 + "m");
			logger.error("---第" + (i+1) + "张工作簿结束---");

			rs.close();
		}

		FileOutputStream fOut = new FileOutputStream(xlsFile);
		wb.write(fOut);
		fOut.flush(); // 刷新缓冲区
		fOut.close();

		long stopTime = System.currentTimeMillis(); // 写文件时间
		logger.error("write xlsx file time: " + (stopTime - startTime) / 1000 + "m");

		// 关闭
		if (isClose) {
			close(stmt, conn);
		}
	}

	// 执行关闭流的操作
	private static void close(Statement stmt, Connection conn) throws SQLException {
		stmt.close();
		conn.close();
	}
	
	//下载
	public static void downloadByNIO2(String url, String saveDir, String fileName) {
		try (InputStream ins = new URL(url).openStream()) {
			Path target = Paths.get(saveDir, fileName);
			Files.createDirectories(target.getParent());
			Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
     * 创建文件夹; 
     * @param path 
     * @return 
     */  
    public static String createFile(String path){  
        File file = new File(path);
        //判断文件是否存在;  
        if(!file.exists()){
            //不存在则创建文件;  
            boolean flag = file.mkdirs();
            if(flag){
                logger.error(path+" 路径创建成功!");
            }else{
                logger.error(path+" 路径创建失败!");
            }
        }
        return path;
    }
    
    /**
	 * 压缩成ZIP
	 * @param srcDir 压缩文件夹路径 
	 * @param out    压缩文件输出流
	 * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构; 
	 * 							false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
	 * @throws RuntimeException 压缩失败会抛出运行时异常
	 */
    public static void toZip(String srcDir, String filename, OutputStream out, boolean KeepDirStructure)
			throws RuntimeException{
		
		long start = System.currentTimeMillis();
		ZipOutputStream zos = null ;
		try {
			zos = new ZipOutputStream(out);
			File sourceFile = new File(srcDir);
			compress(sourceFile,zos,sourceFile.getName(),KeepDirStructure);
			
			long end = System.currentTimeMillis();
			logger.error("压缩完成，耗时：" + (end - start) +" ms");
			
			//获取桌面路径
			FileSystemView fsv = FileSystemView.getFileSystemView();
			File homePath = fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
			downloadByNIO2(PropertiesUtils.APP.getProperty("app.hryfile") + filename + ".zip", homePath.getPath(), filename + ".zip");
		} catch (Exception e) {
			throw new RuntimeException("zip error from ZipUtils",e);
		}finally{
			if(zos != null){
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
			boolean KeepDirStructure) throws Exception{
		byte[] buf = new byte[2 * 1024];
		if(sourceFile.isFile()){
			// 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
			zos.putNextEntry(new ZipEntry(name));
			// copy文件到zip输出流中
			int len;
			FileInputStream in = new FileInputStream(sourceFile);
			while ((len = in.read(buf)) != -1){
				zos.write(buf, 0, len);
			}
			// Complete the entry
			zos.closeEntry();
			in.close();
		} else {
			File[] listFiles = sourceFile.listFiles();
			if(listFiles == null || listFiles.length == 0){
				// 需要保留原来的文件结构时,需要对空文件夹进行处理
				if(KeepDirStructure){
					// 空文件夹的处理
					zos.putNextEntry(new ZipEntry(name + "/"));
					// 没有文件，不需要文件的copy
					zos.closeEntry();
				}
				
			}else {
				for (File file : listFiles) {
					// 判断是否需要保留原来的文件结构
					if (KeepDirStructure) {
						// 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
						// 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
						compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
					} else {
						compress(file, zos, file.getName(),KeepDirStructure);
					}
					
				}
			}
		}
	}
    
    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public static void delete(String fileName) {
    	File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                logger.error("删除单个文件" + fileName + "成功！");
            } else {
                logger.error("删除单个文件" + fileName + "失败！");
            }
        } else {
            logger.error("删除单个文件失败：" + fileName + "不存在！");
        }
    }

	public static void main(String[] args) {
		/*try {
			jdbcex(true, "", "", "ex_entrust",
					"id,userName,surname,trueName,entrustTime,coinCode,fixPriceCoinCode,if(type=1,'买','卖'),if(entrustWay=1,'限价','市价'),entrustPrice,entrustCount,entrustSum,surplusEntrustCount,processedPrice,transactionFee,entrustNum,(case when(status=0) then '未成交' when(status=1) then '部分成交' when(status=2) then '已完成' when(status=3) then '部分成交已撤销' end),if(source=1,'人工','机器人') ",
					"委托人,姓,名,委托时间,交易币种,定价币种,委托类型,出价类型,委托价格,委托数量,委托总金额,成交金额,成交均价,交易手续费,委托单号,状态,来源", 150000);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String p = "D:/tomcat7/tomcat7-6002/wtpwebapps/hurong_trunk3.0_manage/hryfile/excel";
		 p = p.replace("\\hurong_trunk3.0_manage", "").replace("/hurong_trunk3.0_manage", "").replace("/manage", "").replace("\\manage", "");
		 logger.error(p);
		 createFile(p);
	}
}
