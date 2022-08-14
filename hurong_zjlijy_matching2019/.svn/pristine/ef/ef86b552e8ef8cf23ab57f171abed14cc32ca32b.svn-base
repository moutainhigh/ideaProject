package hry.util.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FrontPage implements Serializable{

	private FrontPage () {
	}

	public FrontPage (Map<String, String> params, List assets){
		String limit = params.get("limit");
		String offset = params.get("offset");
		List<?> assetList;
		if(Integer.valueOf(limit)>assets.size()){
			assetList = assets.subList(Integer.valueOf(offset), assets.size());
		}else{
			assetList = assets.subList(Integer.valueOf(offset), Integer.valueOf(limit));
		}
		int pageSize = Double.valueOf(Math.ceil(1.0*assets.size() / Integer.valueOf(limit))).intValue();
		int currentPage = (Integer.valueOf(offset)/Integer.valueOf(limit))+1;

		//设置分页数据
		this.setRows(assetList);
		//设置总记录数
		this.setRecordsTotal((long) assets.size());
		// 设置总记录数
		this.setTotal((long) assets.size());
		this.setPage(currentPage);
		this.setPageSize(pageSize);
	}


	
	
	/**
	 * 返回前台分页对象
	 * @param list   list
	 * @param total  总记录数
	 * @param page   页数
	 * @param pageSize  每页多少条
	 */
	public FrontPage (List list, long total, int page, int pageSize){
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		this.setRows(list);
		//设置总记录数
		this.setRecordsTotal(total);
		// 设置总记录数
		this.setTotal(total);
		this.setPage(page);
		this.setPageSize(pageSize);
		//----------------------分页查询底部外壳------------------------------
		
	}

	private Integer page;// 要查找第几页
	private Integer pageSize;// 每页显示多少条
	private Long totalPage = 0L;// 总页数
	private Long recordsTotal;// 总记录数
	private Long recordsFiltered;// 过滤后记录数
	@SuppressWarnings("rawtypes")
	private List rows;// 结果集
	
	private Long  total;//总记录数
	
	

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

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
			if(this.getPageSize() == 0){
				return 0L;
			}
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
		if(rows==null){
			return new ArrayList<>();
		}
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
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
		return "FrontPage [page=" + page + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", recordsTotal=" + recordsTotal + ", recordsFiltered=" + recordsFiltered + ", rows=" + rows + ", total=" + total + "]";
	}




}
