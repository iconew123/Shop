package shop;

import java.util.ArrayList;

public class ItemManager {

	private static ArrayList<Item> list = new ArrayList<Item>();

	private ItemManager() {

	}

	private static ItemManager instance = new ItemManager();

	public static ItemManager getInstance() {
		return instance;
	}

	public int ItemSize() {
		return list.size();
	}

	public int findDuplName(String name) {
		int index = -1;

		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getName().equals(name))
				index = i;

		return index;
	}

	public int findDuplCode(int code) {
		int index = -1;

		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getCode() == code)
				index = i;

		return index;
	}

	public void printAllItems() {
		if (list.size() == 0) {
			System.err.println("등록된 아이템이 없습니다.");
			return;

		}

		System.out.println("========================");
		for (int i = 0; i < list.size(); i++)
			printOneItems(i);
		System.out.println("========================");
	}

	public void printOneItems(int index) {
		System.out.println(list.get(index));
	}

	public Item buyItem(int index, int quantity) {
		Item tmpItem = list.get(index);
		tmpItem.setQuantity(quantity);

		return tmpItem.clone();
	}

	public boolean addItem(String name, int code, int price) {

		if (findDuplName(name) != -1) {
			System.err.println("중복아이템은 등록 할 수 없습니다.");
			return false;
		}

		list.add(new Item(name, code, price));
		return true;
	}

	public boolean deleteItem(String name) {
		int index = findDuplName(name);

		if (index == -1) {
			System.err.println("일치하는 아이템이 없습니다.");
			return false;
		}

		list.remove(index);
		return true;
	}

	public void changeName(int index, String changeName) {
		list.get(index).setName(changeName);

	}

	public void changePrice(int index, int changePrice) {
		list.get(index).setPrice(changePrice);

	}

}
