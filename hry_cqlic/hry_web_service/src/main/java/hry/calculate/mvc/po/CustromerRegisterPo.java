/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月5日 下午6:40:21
 */
package hry.calculate.mvc.po;

import java.io.Serializable;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月5日 下午6:40:21 
 */
@SuppressWarnings("serial")
public class CustromerRegisterPo implements Serializable {
	
	private Long id;
	private Integer newCustromer;
	private Integer oldCustromer;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNewCustromer() {
		return newCustromer;
	}
	public void setNewCustromer(Integer newCustromer) {
		this.newCustromer = newCustromer;
	}
	public Integer getOldCustromer() {
		return oldCustromer;
	}
	public void setOldCustromer(Integer oldCustromer) {
		this.oldCustromer = oldCustromer;
	}
	public CustromerRegisterPo(Long id, Integer newCustromer,
			Integer oldCustromer) {
		super();
		this.id = id;
		this.newCustromer = newCustromer;
		this.oldCustromer = oldCustromer;
	}
	
	public CustromerRegisterPo() {
		super();
	}
	@Override
	public String toString() {
		return "CustromerRegisterPo [id=" + id + ", newCustromer="
				+ newCustromer + ", oldCustromer=" + oldCustromer + "]";
	}
	
	
	
	
	
}
