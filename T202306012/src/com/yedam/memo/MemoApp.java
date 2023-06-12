package com.yedam.memo;

import java.io.IOException;
import java.util.Scanner;

public class MemoApp {
	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(System.in);
		int choice;

		MemoManager app = new MemoManager();

		while (true) {
			try {
				System.out.println("1.등록 2.검색 3.삭제 4.종료");
				System.out.println("선택> ");
				choice = Integer.parseInt(scn.nextLine());

				switch (choice) {
				case MENU.INSERT:
					app.inputData();
					break;
				case MENU.SEARCH:
					app.searchData();
					break;
				case MENU.Delete:
					app.deleteData();
					break;
				case MENU.EXIT:
					app.storeToFile();
					throw new ExitException();
				}
			} catch (ExitException e) {
				break;
			}
			

		}
		System.out.println("end of prog");
		scn.close();
	}
}
