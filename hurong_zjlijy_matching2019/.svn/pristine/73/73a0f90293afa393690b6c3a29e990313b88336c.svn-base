package hry.web.test.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_biao")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Biao extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private int sumMoney;
	private int nowMoney;
	private String biaoName;
	@Version()
	private Integer version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(int sumMoney) {
		this.sumMoney = sumMoney;
	}

	public int getNowMoney() {
		return nowMoney;
	}

	public void setNowMoney(int nowMoney) {
		this.nowMoney = nowMoney;
	}

	public String getBiaoName() {
		return biaoName;
	}

	public void setBiaoName(String biaoName) {
		this.biaoName = biaoName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Biao(int id, int sumMoney, int nowMoney, String biaoName,
			Integer version) {
		super();
		this.id = id;
		this.sumMoney = sumMoney;
		this.nowMoney = nowMoney;
		this.biaoName = biaoName;
		this.version = version;
	}

	public Biao() {
		super();
	}

	@Override
	public String toString() {
		return "Biao [id=" + id + ", sumMoney=" + sumMoney + ", nowMoney="
				+ nowMoney + ", biaoName=" + biaoName + ", version=" + version
				+ "]";
	}

}
