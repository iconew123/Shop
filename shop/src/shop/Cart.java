package shop;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> list;

	public Cart() {
		list = new ArrayList<Item>();
	}

	public int findMyItem(int code) {
		int index = -1;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getCode() == code)
				index = i;

		return index;
	}

	public void addmyItem(Item buyItem) {
		int index = findMyItem(buyItem.getCode());

		if (index == -1)
			list.add(buyItem);
		else {
			int totalQuantitiy = list.get(index).getQuantity() + buyItem.getQuantity();
			list.get(index).setQuantity(totalQuantitiy);
		}
	}

	public boolean showItems() {
		if (list.size() == 0)
			return false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBan())
				System.err.printf(list.get(i) + "\t총 수량 : %d개\n", list.get(i).getQuantity());
			else
				System.out.printf(list.get(i) + "\t총 수량 : %d개\n", list.get(i).getQuantity());

		}

		return true;
	}

	public void removeItem(int index) {
		list.remove(index);
	}

	public void setMyItemQuantity(int index, int changeQuantity) {
		list.get(index).setQuantity(changeQuantity);
	}

	public void banItem(int code) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getCode() == code)
				list.get(i).setBan();
	}

	public void fixName(int code, String changeName) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getCode() == code)
				list.get(i).setName(changeName);
	}

	public void fixPrice(int code, int changePrice) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getCode() == code)
				list.get(i).setPrice(changePrice);
	}
}
