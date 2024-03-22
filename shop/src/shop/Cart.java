package shop;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> list;

	public Cart() {
		list = new ArrayList<Item>();
	}

	private int findMyItem(int code) {
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
