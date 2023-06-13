package com.yedam.phonebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

//추가, 조회, 삭제, 종료(저장) 
public class PhoneBookManager {

	// 친구이름 중복 허용 x (set)

	HashSet<PhoneInfo> infoStorage = new HashSet<>();
	File dataFile = new File("c:/temp/phonebook.txt");
	File dataStream = new File("c:/temp/phonebook.txt");
	
	
	private static PhoneBookManager instance;

	private PhoneBookManager() {
		readFromFile(); //저장된 정보를 set컬렉션이 초기화
	}

	public static PhoneBookManager getInstance() {
		if (instance == null) {
			instance = new PhoneBookManager();
		}
		return instance;
	}

	// 등록
	public void inputData() throws MenuChoiceException {
		System.out.println("전화번호 구분");
		System.out.println("1.일반 2.대학 3.회사");
		System.out.print("선택>");

		PhoneInfo info = null;
		int menu = MenuViewr.scn.nextInt();
		MenuViewr.scn.nextLine();

		if (menu < INPUT_SELECT.NORMAL || menu > INPUT_SELECT.COMPANAY) {
			throw new MenuChoiceException(menu);
		}
		switch (menu) {
		case INPUT_SELECT.NORMAL:
			info = readFriendInfo();
			break;
		case INPUT_SELECT.UNIV:
			info = readUnivFriendInfo();
			break;
		case INPUT_SELECT.COMPANAY:
			info = readCompanyFriendInfo();
			break;
		}
		boolean isAdded = infoStorage.add(info); //중복값 false, 정상 true;
		if(isAdded) {
			System.out.println("등록완료");
		} else {
			System.out.println("등록오류");
		}
	}
	//친구메소드.
	
	private PhoneInfo readFriendInfo() {
		System.out.println("이름을 입력하셈");
		String name=  MenuViewr.scn.nextLine();
		System.out.println("연락처>");
		String tel = MenuViewr.scn.nextLine();
		
		return new PhoneInfo(name, tel);
		
	}
	//학교친구.
	private PhoneInfo readUnivFriendInfo() {
		System.out.println("이름을 입력하셈");
		String name=  MenuViewr.scn.nextLine();
		System.out.println("연락처>");
		String tel = MenuViewr.scn.nextLine();
		System.out.println("전공은?");
		String major = MenuViewr.scn.nextLine();
		System.out.println("학년은?");
		int year = MenuViewr.scn.nextInt();
		MenuViewr.scn.nextLine();
		
		return new PhoneUnivInfo(name, tel, major, year);
	}
	
	private PhoneInfo readCompanyFriendInfo() {
		System.out.println("이름을 입력하셈");
		String name=  MenuViewr.scn.nextLine();
		System.out.println("연락처>");
		String tel = MenuViewr.scn.nextLine();
		System.out.println("회사는?");
		String company = MenuViewr.scn.nextLine();

		return new PhoneCompanyInfo(name, tel, company);
	}
	
	//종료 storeToStream() => ObjectOutputStream : Serializable
	public void storeToFile() {
		System.out.println("종료합니다.");
		try {
			FileWriter fw = new FileWriter(dataFile);
			Iterator<PhoneInfo> iter = infoStorage.iterator();
			while(iter.hasNext()) {
				fw.write(iter.next().toString());// 이름 연락처 전공 학년
			}
			fw.flush();
			fw.close();
			System.out.println("정상완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//파일읽기. readFromStrea() => ObjectInputStream()
	public void readFromFile() {
		if(!dataFile.exists()) {
			return;
		}
		try {
			FileReader fr = new FileReader(dataFile);
			BufferedReader br = new BufferedReader(fr);
			String str = "";
			PhoneInfo info = null;
			while((str = br.readLine()) != null) { 
				String[] record = str.split(",");
				int kind = Integer.parseInt(record[0]);
				switch(kind) {
				case INPUT_SELECT.NORMAL:
					info = new PhoneInfo(record[1], record[2]);
					break;
				case INPUT_SELECT.UNIV:
					info = new PhoneUnivInfo(record[1], record[2], record[3], Integer.parseInt(record[4]));
					break;
				case INPUT_SELECT.COMPANAY:
					info = new PhoneCompanyInfo(record[1], record[2], record[3]);
				}
				infoStorage.add(info);
			}
			br.close();
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//삭제.
	public void deleteData() {
		System.out.println("이름> ");
		String name = MenuViewr.scn.nextLine();
		
		Iterator<PhoneInfo> iter = infoStorage.iterator();
		while(iter.hasNext()) {
			PhoneInfo curr= iter.next();
			if(curr.getName().equals(name)) {
				iter.remove();
				System.out.println("삭제완료.");
				return;
			}
		}
		System.out.println("삭제할 이름이 없습니다.");
	}
	
		//검색
	public void searchData() {
		System.out.println("이름> ");
		String name = MenuViewr.scn.nextLine();
		
		PhoneInfo info = search(name);
		if(info == null) {
			System.out.println("찾는 이름이 없습니다.");
		} else {
			info.showPhoneInfo();
		}
		
		
	}
	public PhoneInfo search(String name) {
		Iterator<PhoneInfo> iter = infoStorage.iterator();
		while(iter.hasNext()) {
			PhoneInfo curItem = iter.next();
			if(curItem.getName().equals(name)) {
				return curItem;
			}
		}
		return null;
	}
}
