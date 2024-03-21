package shop;

public class Item {
	private String name;
	private int code;
	private int price;
	private int number;

	public Item(String name, int code, int price) {
		this.name = name;
		this.code = code;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return this.code;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
}
