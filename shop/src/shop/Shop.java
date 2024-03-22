package shop;

import java.util.Random;
import java.util.Scanner;

public class Shop {
	private Scanner scan = new Scanner(System.in);
	private Random random = new Random();

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

	private final int ADMIN_ADD_ITEM = 1;
	private final int ADMIN_DELETE_ITEM = 2;
	private final int ADMIN_FIX_ITEM = 3;
	private final int ADMIN_SEARCH = 4;

	private final int ITEM_NAME_FIX = 1;
	private final int ITEM_PRICE_FIX = 2;
	private final int ITEM_SAVE_FIX = 3;

	private final int SHOW_MY_JANG = 1;
	private final int DELET_MY_JANG = 2;
	private final int FIX_MY_JANG = 3;
	private final int PAYMENT = 4;
	private final int CHARGE_MONEY = 5;

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
		else if (sel == ADMIN && checkLogValue(TYPE_IN))
			showAdminMenu();
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
		while (true) {
			itemManager.printAllItems();

			String name = inputString("구매할 아이템 명 입력 (쇼핑종료 exit입력) : ");
			if (name.equals("exit")) {
				System.out.println("메인메뉴 이동");
				return;
			}

			int index = itemManager.findDuplName(name);
			if (index == -1) {
				System.err.println("해당 아이템은 존재하지않습니다.");
				continue;
			}

			int quantity = inputNumber("구매 수량 입력 : ");
			if (quantity < 1) {
				System.err.println("구매수량은 1개이상 입력해주세요.");
				continue;
			}

			Item buyItem = itemManager.buyItem(index, quantity);
			userManager.addMyItem(this.log, buyItem);
		}
	}

	private void myPage() {
		System.out.println("[1] 내 장바구니");
		System.out.println("[2] 항목 삭제");
		System.out.println("[3] 수량 수정");
		System.out.println("[4] 결제");
		System.out.println("[5] 충전하기");

		int pageSel = inputNumber(">> ");

		if (pageSel == SHOW_MY_JANG)
			showMyItems("장바구니");
		else if (pageSel == DELET_MY_JANG)
			deleteMyItems();
		else if (pageSel == FIX_MY_JANG)
			fixMyItems();
		else if (pageSel == PAYMENT)
			payment();
		else if (pageSel == CHARGE_MONEY)
			charge();
		else
			System.err.println("없는기능");
	}

	private boolean showMyItems(String message) {
		boolean isempty = userManager.showUserItems(this.log, message);
		return isempty;
	}

	private void deleteMyItems() {
		if (!showMyItems("장바구니"))
			return;

		int index = userManager.findMyListIndex(this.log, inputNumber("삭제하고 싶은 아이템 코드 입력 : "));
		if (index == -1) {
			System.err.println("일치하는 코드가 없습니다.");
			return;
		}
		userManager.removeMyList(this.log, index);
		System.out.println("삭제완료");

	}

	private void fixMyItems() {
		if (!showMyItems("장바구니"))
			return;

		int index = userManager.findMyListIndex(this.log, inputNumber("수량을 변경하고 싶은 아이템 코드 입력 : "));
		if (index == -1) {
			System.err.println("일치하는 코드가 없습니다.");
			return;
		}

		int changeQuantity = inputNumber("변경 하고 싶은 수량 입력 : ");
		if (changeQuantity < 1) {
			System.err.println("수량은 1개 이상이여햐합니다.");
			return;
		}

		userManager.changeMyListQuantity(this.log, index, changeQuantity);
		System.out.println("수량변경완료");
	}

	private void payment() {
		if (!showMyItems("영수증"))
			return;
		int sum = userManager.sumMyList(this.log);
		int myMoney = userManager.getUserMoney(this.log);
		if (sum == 0) {
			System.err.println("결제가능한 아이템이 없습니다.");
			return;
		} else if (myMoney < sum) {
			System.err.printf("보유 현금이 %d원 부족합니다. 마이페이지에서 충전 후 이용해주세요.\n", (-1) * (myMoney - sum));
			return;
		}

		userManager.buyItems(this.log, myMoney - sum);
		this.total += sum;
		System.out.println("구매완료");

	}

	private void charge() {
		int chargeMoney = inputNumber("입금할 금액 입력 : ");
		if (chargeMoney < 1) {
			System.err.println("1원 이상 입금가능");
			return;
		}
		userManager.setMoney(this.log, chargeMoney);
		System.out.println("충전 완료");
	}

	private void showAdminMenu() {
		System.out.println("[1] 아이템 등록");
		System.out.println("[2] 아이템 삭제");
		System.out.println("[3] 아이템 수정");
		System.out.println("[4] 조회");

		int adminSel = inputNumber(">> ");

		if (adminSel == ADMIN_ADD_ITEM)
			addItem();
		else if (adminSel == ADMIN_DELETE_ITEM)
			deleteItem();
		else if (adminSel == ADMIN_FIX_ITEM)
			fixItem();
		else if (adminSel == ADMIN_SEARCH)
			printTotalSel();
		else
			System.err.println("없는기능");
	}

	private void addItem() {
		String name = inputString("아이템 명 입력 : ");
		int price = inputNumber("아이템 가격 입력 : ");
		int code = 0;
		while (true) {
			code = random.nextInt(9000) + 1000;

			if (itemManager.findDuplCode(code) == -1)
				break;
		}

		if (itemManager.addItem(name, code, price))
			System.out.println("아이템 등록 성공");
		else
			System.err.println("아이템 등록 실패");

	}

	private void deleteItem() {
		itemManager.printAllItems();
		int code = inputNumber("삭제할 아이템 코드 입력 : ");

		if (itemManager.deleteItem(code)) {
			userManager.banMyItem(code);
			System.out.println("아이템 삭제 성공");
		} else
			System.err.println("아이템 삭제 실패");

	}

	private void fixItem() {
		itemManager.printAllItems();
		int code = inputNumber("수정하고 싶은 아이템 코드 입력 : ");
		int index = itemManager.findDuplCode(code);

		if (index == -1) {
			System.err.println("아이템 코드가 일치하지 않습니다.");
			return;
		}

		while (true) {
			showFixMenu();
			int fixSel = inputNumber("수정 할 항목 선택 : ");

			if (fixSel == ITEM_NAME_FIX)
				fixName(code, index);
			else if (fixSel == ITEM_PRICE_FIX)
				fixPrice(code, index);
			else if (fixSel == ITEM_SAVE_FIX)
				break;
			else
				System.err.println("없는 기능입니다.");
		}

	}

	private void printTotalSel() {
		System.out.printf("%s의 총 매출액 : %d원입니다.\n", this.name, this.total);
	}

	private void showFixMenu() {
		System.out.println("[1] 아이템 명 수정");
		System.out.println("[2] 아이템 가격 수정");
		System.out.println("[3] 수정 저장 후 나가기");
	}

	private void fixName(int code, int index) {
		String changeName = inputString("수정 할 아이템명 입력 : ");
		itemManager.changeName(index, changeName);
		userManager.fixMyItemName(code, changeName);
		System.out.println("아이템명 수정 완료");
	}

	private void fixPrice(int code, int index) {
		int changePrice = inputNumber("수정 할 가격 입력 : ");
		itemManager.changePrice(index, changePrice);
		userManager.fixMyItemPrice(code, changePrice);
		System.out.println("아이템가격 수정 완료");
	}

	public void run() {
		while (isRun()) {
			showMenu();
			this.sel = inputNumber("기능 선택 : ");
			choice(sel);
		}
	}
}
