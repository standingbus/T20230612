package com.yedam.memo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.List;


public class FileEx {
	public static void main(String[] args) {
		try {
//			FileOutputStream fos = new FileOutputStream("src/com/yedam/memo/sample.txt");
//			fos.write(10);
//			fos.close();
			//MemoApp.java 파일을 읽어서 화면(console)에 출력.
			InputStream is = new FileInputStream("src/com/yedam/memo/MemoAPP.java");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = "";
			
			while((str = br.readLine()) != null) {
				System.out.println(str);
			}
			br.close();
			isr.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end of");
	}
}
