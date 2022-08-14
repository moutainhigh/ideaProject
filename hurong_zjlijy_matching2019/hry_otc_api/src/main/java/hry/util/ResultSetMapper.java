package hry.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ResusltSet转对应List   
 * @Copyright © 北京互融时代软件有限公司
 * @email: zjjtv@gmail.com 
 * @author: zjj   
 * @date: 2018年8月10日 下午1:23:58 
 */
public class ResultSetMapper<T> {

	public List<T> mapRersultSetToObject(ResultSet rs, Class<T> outputClass) {
		List<T> outputList = null;
		try {
			
			if (rs != null) {
				if (outputClass.isAnnotationPresent(Table.class)) {
					ResultSetMetaData rsmd = rs.getMetaData();
					Field[] fields = outputClass.getDeclaredFields();
					while (rs.next()) {
						T bean = (T) outputClass.newInstance();
						for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
							String columnName = rsmd.getColumnLabel(_iterator + 1);
							Object columnValue = rs.getObject(_iterator + 1);
							for (Field field : fields) {
								if(field.getName().equals(columnName)){
									BeanUtils.setProperty(bean, field.getName(), columnValue);
									break;
								}
//								if (field.isAnnotationPresent(Column.class) ) {
//									Column column = field.getAnnotation(Column.class);
//									if (column.name().equalsIgnoreCase(columnName) && columnValue != null) {
//										BeanUtils.setProperty(bean, field.getName(), columnValue);
//										break;
//									}
//								}
							}
						}
						if (outputList == null) {
							outputList = new ArrayList<T>();
						}
						outputList.add(bean);
					}
 
				} else {
					// 
				}
			} else {
				return null;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return outputList;
	}

}
