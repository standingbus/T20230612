package com.yedam.memo;

public class ExitException extends Exception{
	public ExitException() {
		System.out.println("프로그램들 종료합니다.");
	}
}
