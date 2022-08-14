package hry.manage.mybatis;

import org.apache.log4j.Logger;

import java.sql.*;

public class DBHelper {
    public static final String name = "com.mysql.jdbc.Driver";
    private static Logger logger = Logger.getLogger(DBHelper.class);
    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBHelper(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(AnnotationUtil.jdbc.getProperty("jdbc.url"), AnnotationUtil.jdbc.getProperty("jdbc.username"), AnnotationUtil.jdbc.getProperty("jdbc.password"));//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String sql){
        try {
            DBHelper db = new DBHelper(sql);
            db.pst.execute();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getColumns(String tableName){
        DBHelper db = new DBHelper("show full columns from "+tableName+" ");
        StringBuffer sb = new StringBuffer();
        try {
            ResultSet ret = db.pst.executeQuery();
            while (ret.next()) {
                sb.append(ret.getString(1) + ",");
            }
            ret.close();
            db.close();//关闭连接
            return sb.deleteCharAt(sb.length()-1).toString();
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("表"+tableName+"不存在");
        }
        return null;
    }
}  
