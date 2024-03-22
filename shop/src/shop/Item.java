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

	public Item(String name, int code, int price, int quantity) {
		this.name = name;
		this.code = code;
		this.price = price;
		this.quantity = quantity;
	}

//	public Item(Item item, int quantity) {
//		this.code = item.getCode();
//		this.name = item.getName();
//		this.price = item.getPrice();
//		this.quantity = quantity;
//	}

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

	public Item clone() {
		Item item = new Item(this.name, this.code, this.price, this.quantity);
		return item;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[ %d ]번 아이템 : %s , 가격 : %d원", this.code, this.name, this.price);
	}
}
