/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Yuan Zhicheng
 * @version:      V1.0 
 * @Date:        2015年9月16日 上午11:04:39
 */
package hry.core.mvc.model.page;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页结果集 page 从1开始
 * 
 * @author Yuan Zhicheng
 *
 */

@SuppressWarnings("serial")
public class PageResult implements Serializable {

	private Integer draw; // 请求次数
	private Integer page;// 要查找第几页
	private Integer pageSize;// 每页显示多少条
	private Long totalPage = 0L;// 总页数
	private Long recordsTotal;// 总记录数
	private Long recordsFiltered;// 过滤后记录数
	@SuppressWarnings("rawtypes")
	private List rows;// 结果集

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalPage() {
		if (this.getPageSize() != null) {
			return (this.getRecordsTotal() + this.getPageSize() - 1)
					/ this.getPageSize();// 总页数的算法
		} else {
			return totalPage;
		}
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Long
	 */
	public Long getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Long
	 */
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsTotal;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @return: Integer
	 */
	public Integer getDraw() {
		return draw;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Integer
	 */
	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Long
	 */
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Long
	 */
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	@Override
	public String toString() {
		return "PageResult [draw=" + draw + ", page=" + page + ", pageSize="
				+ pageSize + ", totalPage=" + totalPage + ", recordsTotal="
				+ recordsTotal + ", recordsFiltered=" + recordsFiltered
				+ ", rows=" + rows + "]";
	}

}
