package com.yedam.phonebook;
//PhoneInfo : 이름, 연락처.

//PhoneCompanyInfo ; 회사포함.
//phoneUnivInfo: 학교포함.
//PhoneBookManager: 기능포함
//예외사항 : MenuChoiceException, 메뉴 : INIT_MENU, INPUT_SELECT
//메뉴출력

public class PhoneBookApp {
	public static void main(String[] args) {
		PhoneBookManager app = PhoneBookManager.getInstance();
		int menu;

		// id, pass: id와 passwd를 입력하세요
		// UserCheck check = new UserCheck();
		// check.loginCheck(id,pw);
		// 프로그램 시작.
		UserCheck check = new UserCheck();
		while (true) {
			System.out.println("id와 passw를 입력 (ex) user1 1111 > ");
			String val = MenuViewr.scn.nextLine();
			String[] record = val.split(" ");
			if (check.loginCheck(record[0], record[1])) {
				break;
			} else {

			}
		}

		while (true) {
			try {
				MenuViewr.showMenu();
				menu = MenuViewr.scn.nextInt();
				MenuViewr.scn.nextLine();
				if (menu < INIT_MENU.INPUT || menu > INIT_MENU.EXIT) {
					throw new MenuChoiceException(menu);
				}

				switch (menu) {
				case INIT_MENU.INPUT:
					app.inputData();
					break;
				case INIT_MENU.SEARCH:
					app.searchData();
					break;
				case INIT_MENU.Delete:
					app.deleteData();
					break;
				case INIT_MENU.EXIT:
					app.storeToFile();
					return;
				}
			} catch (MenuChoiceException e) {
				e.showWrongChoice();
			}
		}
	}
}
