/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年5月16日 下午2:54:29
 */
package hry.web.app.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月16日 下午2:54:29 
 */
@SuppressWarnings("serial")
public class AppCategoryVo implements Serializable{
	private Long id;
	private String preateName ;
	private List<AppCategory> list;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPreateName() {
		return preateName;
	}
	public void setPreateName(String preateName) {
		this.preateName = preateName;
	}
	public List<AppCategory> getList() {
		return list;
	}
	public void setList(List<AppCategory> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "AppCategoryVo [id=" + id + ", preateName=" + preateName
				+ ", list=" + list + "]";
	}

	
}
