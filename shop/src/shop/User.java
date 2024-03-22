package shop;

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

	public void setCart(Item buyItem) {
		cart.addmyItem(buyItem);
	}

	public boolean showMyItems() {
		if (!cart.showItems()) {
			System.err.println("장바구니가 비었습니다.");
			return false;
		}
		return true;
	}

	public int findCodeIndex(int code) {
		int index = cart.findMyItem(code);
		return index;
	}

	public void removeMyItem(int index) {
		cart.removeItem(index);
	}

	public void changeQuantity(int index, int changeQuantity) {
		cart.setMyItemQuantity(index, changeQuantity);
	}

	public void banMyItem(int code) {
		cart.banItem(code);
	}

	public void fixMyItemName(int code, String name) {
		cart.fixName(code, name);
	}

	public void fixMyItemPrice(int code, int price) {
		cart.fixPrice(code, price);
	}
}
