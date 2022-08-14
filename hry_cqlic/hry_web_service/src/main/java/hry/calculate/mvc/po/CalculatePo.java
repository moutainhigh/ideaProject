package hry.calculate.mvc.po;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class CalculatePo implements Serializable{
	
	private Long id;
	
	private String codeName;
	
	private String code;
	
	private BigDecimal trancateMoney;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getTrancateMoney() {
		return trancateMoney;
	}

	public void setTrancateMoney(BigDecimal trancateMoney) {
		this.trancateMoney = trancateMoney;
	}

	public CalculatePo(Long id, String codeName, String code,
			BigDecimal trancateMoney) {
		super();
		this.id = id;
		this.codeName = codeName;
		this.code = code;
		this.trancateMoney = trancateMoney;
	}

	public CalculatePo() {
		super();
	}

	@Override
	public String toString() {
		return "CalculatePo [id=" + id + ", codeName=" + codeName + ", code="
				+ code + ", trancateMoney=" + trancateMoney + "]";
	}

	

}
