package test;

import hry.util.properties.PropertiesUtil;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 读取配置文件数据并写入数据库-用于添加语种词汇
 */
public class ReadPropertiesDataToDatabase {
    private static Logger logger = Logger.getLogger(ReadPropertiesDataToDatabase.class);
    // 静态块中不能有非静态属性，所以加static
    private static Properties prop = null;
    //static Logger logger = LoggerFactory.getLogger(ReadPropertiesDataToDatabase.class);

    //静态块中的内容会在类别加载的时候先被执行
    static {
        try {
            prop = new OrderedProperties();
            prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("word.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //静态方法可以被类名直接调用
    public static String getValue(String key) {
        return prop.getProperty(key);
    }

    public static void main (String[] args) {
        Set<String> keyset = prop.stringPropertyNames();
        PreparedStatement ps = null;
        Connection conn = null;
        String[] keysWord = new String[]{"break","case","catch","continue","default",
                "delete","do","else","finally","for","function","if",
                "in","instanceof","new","return","switch","this","throw",
                "try","typeof","var","void","while","with","insert","update",
                "abstract","boolean","byte","char","class","const","debugger",
                "double","enum","export","extends","final","float","goto","implements",
                "import","int","interface","long","native","package","private","protected",
                "public","short","static","super","synchronized","throws","transient","volatile"};
        try {
            //连接mysql数据库
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jiaoyisuo6?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull" , "root", "root");
            conn.setAutoCommit(false);
            //向mysql中插入数据
            String sql = "INSERT INTO app_language (langKey, langVal, langType, langCode, created, modified, saasId, wordSource) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);//要执行sql语句的对象
            // 属于哪个分类，就配置哪个分类，不知道的可以配置成other
            String configType = "";
            String[] langArr = new String[]{"zh_CN","en","kor","jp","zh_TW","fr","es"};
            for (String lang : langArr) {
                for (String key : keyset) {
                    // 过滤不符合规则的语种词汇，否则写在页面js中报错
                    // 规则：允许包含字母、数字、美元符号($)和下划线，但第一个字符不允许是数字，不允许包含空格和其他标点符号
                    String regEx = "[a-zA-Z\\$_][a-zA-Z\\d_]*";
                    Pattern pattern = Pattern.compile(regEx);
                    Matcher matcher = pattern.matcher(key);
                    boolean rs = matcher.matches();
                    // 判断词汇key是否符合js命名规范，并且不是js关键字或保留字
                    if (rs && !Arrays.asList(keysWord).contains(key)) {
                        if (!"".equals(configType)) {
                            // 写入数据库
                            ps.setString(1, key);
                            ps.setString(2, getValue(key));
                            ps.setString(3, configType);
                            ps.setString(4, lang); // 语种
                            Date date = new Date();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String d = df.format(date);
                            ps.setTimestamp(5, Timestamp.valueOf(d));
                            ps.setTimestamp(6, Timestamp.valueOf(d));
                            ps.setString(7, "hurong_system");
                            // 手机端：app, 电脑端：pc
                            ps.setString(8, "pc");
                            ps.addBatch();//再添加一次预定义参数
                        }
                    } else {
                        logger.error("分类词汇 : " + key);
                        if (key.startsWith("[") && key.endsWith("]")) {
                            configType = key.trim().substring(1, key.indexOf("]"));
                        }
                    }
                }
            }
            ps.executeBatch();//执行批量执行
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
