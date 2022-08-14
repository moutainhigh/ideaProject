package hry.manage.mybatis;

import hry.annotation.MyColumn;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;


/**
 * 
 * MybatisGeneration.java
 * @author denghf
 * 2018年2月13日 下午4:19:03
 */
public class MybatisGeneration {
	private static Logger logger = Logger.getLogger(MybatisGeneration.class);
	public static Properties jdbc;

	public static void generation(){
		InputStream insjdbc = MybatisGeneration.class.getClassLoader().getResourceAsStream("jdbc.properties");
        jdbc = new Properties();
        try {
            jdbc.load(insjdbc);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		InputStream ins = MybatisGeneration.class.getClassLoader().getResourceAsStream("code.properties");
		Properties pro = new Properties();
		try {
			pro.load(ins);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String modelUrl = pro.getProperty("modelUrl");
		
		if(!StringUtils.isEmpty(modelUrl)){
			logger.error("modelUrl  ==  " + modelUrl);
			
			String[] str = modelUrl.split(",");
			if(str.length>0){
				try {
					for(int i=0;i<str.length;i++){
						Class<?> forName = Class.forName(str[i]);
						if(forName!=null){
							Field[] fs = forName.getDeclaredFields();
							
							//先拿到@Table
							Table annotationTable = forName.getAnnotation(Table.class);
							String columns = "";
							if(annotationTable!=null){
								columns = DBHelper.getColumns(annotationTable.name());
							}
							
							if(!"".equals(columns)){
								for(Field field : fs){
									if(!field.isAccessible()){  
										field.setAccessible(true);  
					                }
									StringBuffer sb = new StringBuffer("ALTER TABLE "+ annotationTable.name() +" ADD ");
									
									//取字段上@MyColumn的name
									MyColumn annotationColumn = field.getAnnotation(MyColumn.class);
									if(annotationColumn!=null){
										if(!"".equals(annotationColumn.name()) && !columns.contains(annotationColumn.name())){
											//如果不包含，说明这个字段实体有，数据库没有
											
											//先创建这个字段
											sb.append(annotationColumn.name() +" ");
											//判断类型
											if("text".equals(annotationColumn.type())){
												sb.append(annotationColumn.type());
											}else if("datetime".equals(annotationColumn.type())){
												sb.append("datetime ");
											}else if("decimal".equals(annotationColumn.type())){
												sb.append("DECIMAL(" + annotationColumn.precision() + "," + annotationColumn.scale() + ") ");
											}else{
												sb.append("" + annotationColumn.type() + "("+ annotationColumn.length() +") ");
											}
											//是否为空
											if(annotationColumn.nullable()){
												sb.append("DEFAULT NULL ");
											}else{
												sb.append("DEFAULT NOT NULL ");
											}
											//是否唯一
											if(annotationColumn.unique()){
												sb.append("UNIQUE ");
											}
											//注释
											if(!"".equals(annotationColumn.comment())){
												sb.append("COMMENT '"+ annotationColumn.comment() +"'");
											}
											logger.error("sql  ==  " + sb.toString());
											DBHelper.execute(sb.toString());
											
											//字段上是否含有@Id 和 @GeneratedValue,一般这两个字段是一起用的
											Id annotationId = field.getAnnotation(Id.class);
											GeneratedValue annotationGeneratedValue = field.getAnnotation(GeneratedValue.class);
											if(annotationId!=null && annotationGeneratedValue!=null){
												String sql = "alter table "+ annotationTable.name() +" change "+ annotationColumn.name() +" "+ annotationColumn.name() +" "+ annotationColumn.type() +" AUTO_INCREMENT PRIMARY KEY";
												DBHelper.execute(sql.toString());
											}
										}
									}
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		MybatisGeneration.generation();
	}
}
