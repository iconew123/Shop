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

	public boolean join(String name, String id, String password) {
		if (isDuplId(id) != -1) {
			System.err.println("중복아이디입니다.");
			return false;
		}

		list.add(new User(name, id, password));
		return true;
	}

	private int isDuplId(String id) {
		int index = -1;

		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId().equals(id))
				index = i;

		return index;
	}

}
