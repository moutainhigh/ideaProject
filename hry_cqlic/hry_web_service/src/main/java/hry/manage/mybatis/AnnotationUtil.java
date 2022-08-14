package hry.manage.mybatis;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;




public class AnnotationUtil {
    public static Properties jdbc;
    private static Logger logger = Logger.getLogger(AnnotationUtil.class);
    public static void validAnnotation(List<Class<?>> clsList) {
        InputStream insjdbc = AnnotationUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        jdbc = new Properties();
        try {
            jdbc.load(insjdbc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //开始加字段
        Long start = System.currentTimeMillis();
        if (clsList != null && clsList.size() > 0) {

            for (Class<?> forName : clsList) {
                if (forName != null) {
                    Field[] fs = forName.getDeclaredFields();


                    //先拿到@Table
                    Table annotationTable = forName.getAnnotation(Table.class);
                    String columns = "";
                    if (annotationTable != null) {
                        String name = "";
                        try {
                            name = annotationTable.name();
                            columns = DBHelper.getColumns(name);
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error(name);
                            continue;
                        }

                    } else {//如果不是实体类直接跳过
                        continue;
                    }

                    if (!"".equals(columns) && null != columns) {
                        for (Field field : fs) {
                            if (!field.isAccessible()) {
                                field.setAccessible(true);
                            }
                            StringBuffer sb = new StringBuffer("ALTER TABLE " + annotationTable.name() + " ADD ");

                            //取字段上@Column的name
                            Column annotationColumn = field.getAnnotation(Column.class);
                            if (annotationColumn != null) {
                            	try {
                                    if (!"".equals(annotationColumn.name()) && !columns.contains(annotationColumn.name()) && annotationColumn.columnDefinition() != null && (!"".equals(annotationColumn.columnDefinition()))) {
                                        //如果不包含，说明这个字段实体有，数据库没有
                                    	logger.error(annotationColumn.name());
                                        //先创建这个字段
                                        sb.append(annotationColumn.name() + " ");
                                        //判断类型
                                        sb.append(annotationColumn.columnDefinition());


                                        //是否为空
                                        /*if (annotationColumn.nullable()) {
                                            sb.append("DEFAULT NULL ");
                                        } else {
                                            sb.append("DEFAULT NOT NULL ");
                                        }*/
                                        //是否唯一
                                        /*if (annotationColumn.unique()) {
                                            sb.append("UNIQUE ");
                                        }
*/
                                        logger.error("sql  ==  " + sb.toString());
                                        DBHelper.execute(sb.toString());
                                        //logger.error(sb);

                                        //字段上是否含有@Id 和 @GeneratedValue,一般这两个字段是一起用的
                                        Id annotationId = field.getAnnotation(Id.class);
                                        GeneratedValue annotationGeneratedValue = field.getAnnotation(GeneratedValue.class);
                                        if (annotationId != null && annotationGeneratedValue != null) {
                                            String sql = "alter table " + annotationTable.name() + " change " + annotationColumn.name() + " " + annotationColumn.name() + " " + annotationColumn.columnDefinition() + " AUTO_INCREMENT PRIMARY KEY";
                                            DBHelper.execute(sql.toString());
                                            logger.error(sql);
                                        }
                                    }
								} catch (Exception e) {
									//e.printStackTrace();
									logger.error(annotationTable.name());
								}
                      
                            }
                        }
                    }
                }
            }
        }
        logger.error("加字段结束,耗时" + (System.currentTimeMillis() - start) / 1000 + "秒");
    }

}