/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年5月12日 上午9:40:02
 */
package hry.web.app.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年5月12日 上午9:40:02
 * 
 * 
 * 此类用户封装一个类别下的所有文章
 * 
 */

@SuppressWarnings("serial")
public class AppArticleVo implements Serializable{

	private Long id;
	// 类别名
	private String name;
	// 某个类别下的所有 数据
	private List<AppArticle> list;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppArticle> getList() {
		return list;
	}

	public void setList(List<AppArticle> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "AppArticleVo [id=" + id + ", name=" + name + ", list=" + list
				+ "]";
	}

}
