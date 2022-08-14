package hry.web.test.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_detail")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Detail extends BaseModel{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private int toBiaoMoney;
	private String biaoName;
	private String userName;
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getToBiaoMoney() {
		return toBiaoMoney;
	}

	public void setToBiaoMoney(int toBiaoMoney) {
		this.toBiaoMoney = toBiaoMoney;
	}

	public String getBiaoName() {
		return biaoName;
	}

	public void setBiaoName(String biaoName) {
		this.biaoName = biaoName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Detail(int id, int toBiaoMoney, String biaoName, String userName,
			String date) {
		super();
		this.id = id;
		this.toBiaoMoney = toBiaoMoney;
		this.biaoName = biaoName;
		this.userName = userName;
		this.date = date;
	}

	public Detail() {
		super();
	}

	@Override
	public String toString() {
		return "Detail [id=" + id + ", toBiaoMoney=" + toBiaoMoney
				+ ", biaoName=" + biaoName + ", userName=" + userName
				+ ", date=" + date + "]";
	}

}
