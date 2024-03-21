package shop;

import java.util.Scanner;

public class Shop {
	private Scanner scan = new Scanner(System.in);

	private UserManager userManager = UserManager.getInstance();
	private ItemManager itemManager = ItemManager.getInstance();

	private String name;
	private int sel;
	private int log;
	private int total;

	public Shop(String name) {
		this.name = name;
		log = -1;
		sel = -1;
		total = 0;
	}

	private boolean isRun() {
		return sel != 0 ? true : false;
	}

	private int inputNumber(String text) {
		int number = -1;
		System.out.print(text);

		try {
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("숫자만 입력하세요");
		}

		return number;
	}

	private String inputString(String text) {
		System.out.print(text);
		return scan.next();
	}

	private void showMenu() {
		System.out.printf("[ %s ]\n", this.name);
		System.out.println("[1] 회원가입");
		System.out.println("[2] 회원탈퇴");
		System.out.println("[3] 로그인");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 쇼핑하기");
		System.out.println("[6] 마이페이지");
		System.out.println("[7] 관리자");
		System.out.println("[0] 종료");
		// 자동저장 / 로그
	}

	private final int JOIN = 1;
	private final int WITHDRAWAL = 2;
	private final int LOGIN = 3;
	private final int LOGOUT = 4;
	private final int SHOPPING = 5;
	private final int MYPAGE = 6;
	private final int ADMIN = 7;
	private final int END = 0;

	private void choice(int sel) {
		if (sel == JOIN)
			join();
		else if (sel == WITHDRAWAL)
			withdrawal();
		else if (sel == LOGIN)
			login();
		else if (sel == LOGOUT)
			logout();
		else if (sel == SHOPPING)
			shopping();
		else if (sel == MYPAGE)
			myPage();
		else if (sel == ADMIN)
			showAdminMenu();
		else if (sel == END)
			System.out.println("프로그램 종료");
		else
			System.err.println("없는기능입니다.");
	}

	private void join() {
		// 동명이인 가능 <> 같은아이디 불가능
		String name = inputString("이름 입력 : ");
		String id = inputString("사용할 아이디 입력 : ");
		String pw = inputString("비밀번호 설정 : ");
		if(userManager.join(name, id, pw))
			System.out.println("회원가입 성공");
	}

	private void withdrawal() {

	}

	private void login() {

	}

	private void logout() {

	}

	private void shopping() {

	}

	private void myPage() {

	}

	private void showAdminMenu() {

	}

	public void run() {
		while (isRun()) {
			showMenu();
			this.sel = inputNumber("기능 선택 : ");
			choice(sel);
		}
	}
}
