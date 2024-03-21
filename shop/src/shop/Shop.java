package shop;

import java.util.Scanner;

public class Shop {
	private Scanner scan = new Scanner(System.in);

	private UserManager userManager = UserManager.getInstance();
	private ItemManager itemManager = ItemManager.getInstance();

	private final int TYPE_IN = 1;
	private final int TYPE_OUT = 2;

	private final int JOIN = 1;
	private final int WITHDRAWAL = 2;
	private final int LOGIN = 3;
	private final int LOGOUT = 4;
	private final int SHOPPING = 5;
	private final int MYPAGE = 6;
	private final int ADMIN = 7;
	private final int END = 0;

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
		
		System.out.println(log != -1 ? String.format(userManager.showName(log) + "님 로그인 중...") : "로그인이 필요합니다!!");
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

	private boolean checkLogValue(int logState) {
		if (log > -1 && logState == TYPE_OUT) {
			System.err.println("로그아웃 후 이용해주세요");
			return false;
		} else if (log == -1 && logState == TYPE_IN) {
			System.err.println("로그인 후 이용해주세요");
			return false;
		}

		return true;
	}

	private void choice(int sel) {
		if (sel == JOIN && checkLogValue(TYPE_OUT))
			join();
		else if (sel == WITHDRAWAL && checkLogValue(TYPE_IN))
			withdrawal();
		else if (sel == LOGIN && checkLogValue(TYPE_OUT))
			login();
		else if (sel == LOGOUT)
			logout();
		else if (sel == SHOPPING && checkLogValue(TYPE_IN))
			shopping();
		else if (sel == MYPAGE && checkLogValue(TYPE_IN))
			myPage();
//		else if (sel == ADMIN && checkLogValue(TYPE_IN))
//			showAdminMenu();
		else if (sel == END)
			System.out.println("프로그램 종료");
	}

	private void join() {
		// 동명이인 가능 <> 같은아이디 불가능
		String name = inputString("이름 입력 : ");
		String id = inputString("사용할 아이디 입력 : ");
		String pw = inputString("비밀번호 설정 : ");
		if (userManager.join(name, id, pw))
			System.out.println("회원가입 성공");
		else
			System.err.println("회원가입 실패");
	}

	private void withdrawal() {
		if (this.log == 0) {
			System.err.println("ADMIN 계정은 탈퇴할 수 없습니다.");
			return;
		}

		String pw = inputString("비밀번호 재입력 : ");
		if (userManager.userWithDrawal(log, pw)) {
			log = -1;
			System.out.println("탈퇴 완료");
		} else
			System.err.println("탈퇴 실패");
	}

	private void login() {
		String id = inputString("ID : ");
		String pw = inputString("PW : ");

		log = userManager.findUserLog(id, pw);
		if (log != -1)
			System.out.println("로그인 성공");
		else
			System.err.println("로그인 실패");
	}

	private void logout() {
		log = -1;
		System.out.println("로그아웃완료");
	}

	private void shopping() {
		
	}

	private void myPage() {

	}

	public void run() {
		while (isRun()) {
			showMenu();
			this.sel = inputNumber("기능 선택 : ");
			choice(sel);
		}
	}
}
