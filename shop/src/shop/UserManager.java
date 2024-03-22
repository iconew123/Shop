package shop;

import java.util.ArrayList;

public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();
	private FileManager fileManager = FileManager.getInstance();

	// list의 첫자리는 admin
	private UserManager() {
		if (!fileManager.isExsistUserFile())
			list.add(new User("ADMIN", "ADMIN", "1Q2W3E4R"));
	}

	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}

	public String showName(int log) {
		return list.get(log).getName();
	}

	public boolean join(String name, String id, String password) {
		if (isDuplId(id) != -1) {
			System.err.println("중복아이디입니다.");
			return false;
		}

		list.add(new User(name, id, password));
		return true;
	}

	public boolean userWithDrawal(int log, String password) {
		if (!list.get(log).getPassword().equals(password)) {
			System.err.println("비밀번호 불일치");
			return false;
		}

		list.remove(log);
		return true;
	}

	public int findUserLog(String id, String password) {
		int index = -1;

		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId().equals(id))
				index = i;

		if (index == -1) {
			System.err.println("일치하는 아이디가 없습니다.");
			return index;
		} else if (!list.get(index).getPassword().equals(password)) {
			System.err.println("비밀번호가 다릅니다.");
			return -1;
		} else
			return index;
	}

	private int isDuplId(String id) {
		int index = -1;

		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId().equals(id))
				index = i;

		return index;
	}

	public void addMyItem(int log, Item buyItem) {
		list.get(log).setCart(buyItem);
	}

	public boolean showUserItems(int log, String message) {
		System.out.printf("===================%s님의 %s 목록===================\n", list.get(log).getName(), message);
		boolean isShow = list.get(log).showMyItems();
		System.out.println("=======================================================");

		return isShow;
	}

	public int findMyListIndex(int log, int code) {
		int index = list.get(log).findCodeIndex(code);
		return index;
	}

	public void removeMyList(int log, int index) {
		list.get(log).removeMyItem(index);
	}

	public void changeMyListQuantity(int log, int index, int changeQuantity) {
		list.get(log).changeQuantity(index, changeQuantity);
	}

	public int sumMyList(int log) {
		int sum = list.get(log).sumMyItems();
		return sum;
	}

	public void banMyItem(int code) {
		for (int i = 0; i < list.size(); i++)
			list.get(i).banMyItem(code);
	}

	public void fixMyItemName(int code, String changeName) {
		for (int i = 0; i < list.size(); i++)
			list.get(i).fixMyItemName(code, changeName);
	}

	public void fixMyItemPrice(int code, int changePrice) {
		for (int i = 0; i < list.size(); i++)
			list.get(i).fixMyItemPrice(code, changePrice);
	}

	public int getUserMoney(int log) {
		return list.get(log).getMoney();
	}

	public void setMoney(int log, int money) {
		list.get(log).setMoney(money);
	}

	public void buyItems(int log, int money) {
		setMoney(log, money);
		list.get(log).removeAllMyList();
	}

	public void userAutoSave() {
		String textUser = "";
		for (int i = 0; i < list.size(); i++) {
			textUser += list.get(i).oneUserInfo();
			if (i < list.size() - 1)
				textUser += "\n";
		}
		fileManager.autoSaveUserList(textUser);
	}

	public void userAutoLoad() {
		int log = 0;
		if (fileManager.isExsistUserFile()) {
			String[] loadData = fileManager.autoLoadUserData().split("\n");
			for (int i = 0; i < loadData.length; i++) {
				String[] oneLine = loadData[i].split("/");
				User user = loadUser(oneLine);
				list.add(user);
				if (!oneLine[4].equals("null"))
					loadMyList(log, oneLine[4]);
				log++;
			}
		} else
			System.out.println("로드 파일이 없습니다.");
	}

	private User loadUser(String[] oneLine) {
		String name = oneLine[0];
		String id = oneLine[1];
		String pw = oneLine[2];
		int money = Integer.parseInt(oneLine[3]);

		return new User(name, id, pw, money);
	}

	private void loadMyList(int log, String textMyList) {
		String[] spMyList = textMyList.split("~");
		for (int i = 0; i < spMyList.length; i++) {
			String[] oneMyList = spMyList[i].split(",");
			Item item = loadItem(oneMyList);
			addMyItem(log, item);
		}
	}

	private Item loadItem(String[] oneMyList) {
		int code = Integer.parseInt(oneMyList[0]);
		String name = oneMyList[1];
		int price = Integer.parseInt(oneMyList[2]);
		int quantity = Integer.parseInt(oneMyList[3]);
		boolean ban = Boolean.parseBoolean(oneMyList[4]);
		return new Item(name, code, price, quantity, ban);
	}
}
