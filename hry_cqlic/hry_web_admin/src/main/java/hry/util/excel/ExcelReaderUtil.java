package hry.util.excel;

import hry.admin.exchange.controller.KLineHistoryController;
import hry.bean.JsonResult;
import hry.util.klinedata.TransactionOrder;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ExcelReaderUtil 事件模式读取  强大！
 *
 * @author: denghf
 * @version: V1.0
 * @Date: 2018/11/1 19:31
 */
public class ExcelReaderUtil {

    private static final Logger log = Logger.getLogger(ExcelReaderUtil.class);

    private static final int PAGE_SIZE = 2000;

    private static final int MONTH_PAGE_SIZE = 43200;

    //excel2003扩展名
    public static final String EXCEL03_EXTENSION = ".xls";
    //excel2007扩展名
    public static final String EXCEL07_EXTENSION = ".xlsx";

    public static final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(1);

    public static final ThreadLocal<Map<String, Object>> tl = new ThreadLocal<Map<String, Object>>();

    public static JsonResult kLineExcel(MultipartFile file){
        JsonResult jsonResult = new JsonResult();

        try {
            while (queue.size() == 0) {

            }
            jsonResult = readExcel(file);

            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Map<String, Object> map = tl.get();
            List<TransactionOrder> list = (List<TransactionOrder>)map.get("list");
            Collections.reverse(list);
            if(list.size() > MONTH_PAGE_SIZE){
                list = list.subList(0, MONTH_PAGE_SIZE);
            }
            if(list.size() > PAGE_SIZE){
                //对list分页
                int count = 0;
                if(list.size() % PAGE_SIZE == 0){
                    count = list.size() / PAGE_SIZE;
                }else{
                    count = list.size() % PAGE_SIZE + 1;
                }
                int num = list.size(); // 5001
                for(int i=0; i<count; i++){
                    if(num > PAGE_SIZE){
                        List<TransactionOrder> listc = list.subList(i * PAGE_SIZE, (i + 1) * PAGE_SIZE);
                        KLineHistoryController.common(listc.get(listc.size() - 1).getTransactionTime(), listc.get(0).getTransactionTime(), map.get("tradeCoinCode").toString(), map.get("priceCoinCode").toString(), listc, sim);
                        //KLineHistoryController.common(listc.get(listc.size() - 1).getTransactionTime(), listc.get(0).getTransactionTime(), "AAAA", "USDT", listc, sim);
                        num = num - PAGE_SIZE;
                    }else {
                        List<TransactionOrder> listc = list.subList(i * PAGE_SIZE, list.size());
                        KLineHistoryController.common(listc.get(listc.size() - 1).getTransactionTime(), listc.get(0).getTransactionTime(), map.get("tradeCoinCode").toString(), map.get("priceCoinCode").toString(), listc, sim);
                        //KLineHistoryController.common(listc.get(listc.size() - 1).getTransactionTime(), listc.get(0).getTransactionTime(), "AAAA", "USDT", listc, sim);
                    }
                }
            }else{
                KLineHistoryController.common(list.get(list.size() - 1).getTransactionTime(), list.get(0).getTransactionTime(), map.get("tradeCoinCode").toString(), map.get("priceCoinCode").toString(), list, sim);
                //KLineHistoryController.common(list.get(list.size() - 1).getTransactionTime(), list.get(0).getTransactionTime(), "AAAA", "USDT", list, sim);
            }
            list.clear();
        }catch (Exception e){
            e.printStackTrace();
            return jsonResult.setSuccess(false);
        }finally {
            queue.poll();

            log.info("队列 ： size:" + queue.size());
        }
        return jsonResult;
    }

    /**
     * 每获取一条记录，即打印
     * 在flume里每获取一条记录即发送，而不必缓存起来，可以大大减少内存的消耗，这里主要是针对flume读取大数据量excel来说的
     * @param sheetName
     * @param sheetIndex
     * @param curRow
     * @param cellList
     */
    public static void sendRows(String filePath, String sheetName, int sheetIndex, int curRow, List<String> cellList) {
        try{
            SimpleDateFormat simf = new SimpleDateFormat("yyyyMMddHHmm");
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            TransactionOrder t = new TransactionOrder();
            t.setStartPrice(new BigDecimal(cellList.get(0).toString()));
            t.setMaxPrice(new BigDecimal(cellList.get(1).toString()));
            t.setMinPrice(new BigDecimal(cellList.get(2).toString()));
            t.setEndPrice(new BigDecimal(cellList.get(3).toString()));
            t.setTransactionCount(new BigDecimal(cellList.get(4).toString()));
            try {
                t.setTransactionTime(sim.format(sim.parse(cellList.get(5).replace("/","-"))));
                t.setTransactionEndTime(sim.format(sim.parse(cellList.get(6).replace("/","-"))));
                t.setId((simf.parse(cellList.get(5).replace("/","-"))).getTime()/1000 + "");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Map<String, Object> map = tl.get();
            List<TransactionOrder> list = (List<TransactionOrder>)map.get("list");
            list.add(t);


            /*StringBuffer oneLineSb = new StringBuffer();
            oneLineSb.append(filePath);
            oneLineSb.append("--");
            oneLineSb.append("sheet" + sheetIndex);
            oneLineSb.append("::" + sheetName);//加上sheet名
            oneLineSb.append("--");
            oneLineSb.append("row" + curRow);
            oneLineSb.append("::");
            for (String cell : cellList) {
                oneLineSb.append(cell.trim());
                oneLineSb.append("|");
            }
            String oneLine = oneLineSb.toString();
            if (oneLine.endsWith("|")) {
                // 去除最后一个分隔符
                oneLine = oneLine.substring(0, oneLine.lastIndexOf("|"));
            }*/

            //System.out.println(oneLine);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static JsonResult readExcel(MultipartFile file) throws Exception {
        int totalRows =0;
        //获得文件名
        String fileName = file.getOriginalFilename();
        if (fileName.endsWith(EXCEL03_EXTENSION)) { //处理excel2003文件
            ExcelXlsReader excelXls = new ExcelXlsReader();
            totalRows = excelXls.process(fileName);
            log.info("excel版本为2003以后，总行数：" + totalRows);
            return new JsonResult().setSuccess(true).setMsg("导入完成！");
        } else if (fileName.endsWith(EXCEL07_EXTENSION)) {//处理excel2007文件
            ExcelXlsxReader excelXlsxReader = new ExcelXlsxReader();
            totalRows = excelXlsxReader.process(file);
            log.info("excel版本为2007以后，总行数：" + totalRows);
            return new JsonResult().setSuccess(true).setMsg("导入完成！");
        } else {
            return new JsonResult().setSuccess(false).setMsg("文件格式错误");
        }
    }

    public static void main(String[] args) throws Exception {
        /*long startTime=System.currentTimeMillis();   //获取开始时间

        String path="C:\\Users\\yanfeng\\Desktop\\123.xlsx";
        ExcelReaderUtil.readExcel(path);

        long endTime=System.currentTimeMillis(); //获取结束时间

        System.out.println("程序运行时间： "+(endTime - startTime)+"ms");*/
        List<String> list = new ArrayList<String>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        System.out.println(list.subList(0, 3));
    }
}
