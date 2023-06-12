package com.yedam.memo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemoManager {
	List<Memo> storage = new ArrayList<>();
	Scanner scn = new Scanner(System.in);

	
	
	// 등록.
	public void inputData() {
		System.out.println("번호> ");
		int no = Integer.parseInt(scn.nextLine());
		System.out.println("날짜> ");
		String date = scn.nextLine();
		System.out.println("내용> ");
		String content = scn.nextLine();

		storage.add(new Memo(no, date, content));
	}

	// 조회.
	public void searchData() {
		System.out.println("날짜>");
		String date = scn.nextLine();
		boolean isYn = false;
		for (int i = 0; i < storage.size(); i++) {
			if(date.equals(storage.get(i).getDate())) {
				System.out.println(storage.get(i));
				isYn = true;
			}
		}
		if(!isYn) {
			System.out.println("해당 날짜의 메모가 없습ㄴ디ㅏ.");
		}
	}
	// 삭제.
	public void deleteData() {
		System.out.println("번호> ");
		int num = Integer.parseInt(scn.nextLine());
		for (int i = 0; i < storage.size(); i++) {
			if(storage.get(i).getNo() == num) {
				storage.remove(i);
			}
		}
	}
	public void readFromFile() {
		try {
			FileInputStream fis = new FileInputStream("c:/temp/memobook.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			storage = (List<Memo>) ois.readObject();
			ois.close();
			fis.close();
			
		} catch (FileNotFoundException | ClassNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("계속진햏하세요.");
		} catch(IOException e) {
//			e.printStackTrace();
		}
	}
	
	// 파일저장.
	public void storeToFile() throws IOException {
		try {
			FileOutputStream fos = new FileOutputStream("c:/temp/memobook.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(storage);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
