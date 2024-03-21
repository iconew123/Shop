package shop;

import java.util.ArrayList;

public class User {
	private String name;
	private String id;
	private String password;
	private int money;
	private Cart cart;

	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
		money = 10000;
		cart = new Cart();
	}

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
