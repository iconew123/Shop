package shop;

public class Item {
	private int code;
	private String name;
	private int price;
	private int quantity;

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
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[ %d ]번 아이템 : %s , 가격 : %d원", this.code , this.name , this.price);
	}
}
