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
@Table(name = "t_user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User extends BaseModel{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	private String name;
	private int fundnum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFundnum() {
		return fundnum;
	}

	public void setFundnum(int fundnum) {
		this.fundnum = fundnum;
	}

	public User(int id, String name, Integer i) {
		super();
		this.id = id;
		this.name = name;
		this.fundnum = i;
	}

	public User() {
		super();
	}

}
