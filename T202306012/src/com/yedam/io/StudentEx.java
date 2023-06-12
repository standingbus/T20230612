package com.yedam.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentEx {
	public static void main(String[] args) {

		// c:/temp/students.txt 학생의 점수의 평균
		// 최고점수의 학생이름과 최고점수 출력.
		try {
			method2();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("평균 : 82.");
//		System.out.println("최고점수는  90점, 학생은 김인수");
	}

	public static void method2() throws IOException {
		InputStream is = new FileInputStream("c:/temp/student.txt");
		InputStreamReader isr = new InputStreamReader(is);
		char[] buf = new char[200];
		isr.read(buf);// read 입력테스트 통해서 buf 저장

		String str = new String(buf); // new String(char[])
//		System.out.println(str);
		String[] strAry = str.split("\n");
		for (String student : strAry) {
			if (student != null) {
				String[] std = student.split(" ");
				System.out.printf("학생번호 %s, 이름 %s, 점수 %s", std[0], std[1], std[2]);
			}
		}

		isr.close();
		is.close();
	}

	public static void method() throws IOException {
		Scanner scn = new Scanner(new File("c:/temp/student.txt"));
		String name = "";
		int sum = 0, maxScore = 0;
		int cnt = 0;
		double avg = 0;
		while (true) {
			String values = "";
			try {
				values = scn.nextLine();
			} catch (NoSuchElementException e) {
				break;
			}

			String[] valAry = values.split(" ");
			sum += Integer.parseInt(valAry[2]);
			cnt++;
			if (maxScore < Integer.parseInt(valAry[2])) {
				maxScore = Integer.parseInt(valAry[2]);
				name = valAry[1];
			}
		}
		avg = (double) sum / cnt;
		System.out.printf("평균점수 : %.2f ", avg);
		System.out.printf("최고점수 %d, 학생이름 %s \n", maxScore, name);
	}

}
