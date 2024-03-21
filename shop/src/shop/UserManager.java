package shop;

import java.util.ArrayList;

public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();

	// list의 첫자리는 admin
	private UserManager() {
		list.add(new User("ADMIN", "ADMIN", "1q2w3e4r"));
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

}
