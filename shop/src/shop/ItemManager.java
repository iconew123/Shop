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
	
}
