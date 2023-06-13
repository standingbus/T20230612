package com.yedam.phonebook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;

class User {
	String id;
	String pw;

	User(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

}

public class UserCheck {

	HashSet<User> userList = new HashSet<>();

	UserCheck() {
		readFromFile();
	}

	// id, passwd를 입력받으
	public boolean loginCheck(String id, String pw) {
		Iterator<User> itr = userList.iterator();

		while (itr.hasNext()) {
			User user = itr.next();
			if (user.id.equals(id) && user.pw.equals(pw)) {
				return true;
			}
		}
		return false;
	}

	public void readFromFile() {
		
		try {
			FileReader fr = new FileReader("c:/temp/userlist.txt");
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String temp = br.readLine();
				if (temp == null) {
					break;
				}
				String[] record = temp.split(" ");
				
				userList.add(new User(record[0], record[1]));
			}
			fr.close();
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// c://temp/userList
	}
}
