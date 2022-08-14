package hry.web.test.model;

public class SendObj {
	private String name;
	private int money;
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SendObj(String name, int money, int id) {
		super();
		this.name = name;
		this.money = money;
		this.id = id;
	}

	@Override
	public String toString() {
		return "SendObj [name=" + name + ", money=" + money + ", id=" + id
				+ "]";
	}

}
