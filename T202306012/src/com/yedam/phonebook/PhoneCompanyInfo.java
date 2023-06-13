package com.yedam.phonebook;

public class PhoneCompanyInfo extends PhoneInfo {
	private String company;

	public PhoneCompanyInfo(String name, String phoneNumber, String company) {
		super(name, phoneNumber);
		this.company = company;
	}

	@Override
	public void showPhoneInfo() {
		super.showPhoneInfo();
		this.company = company;
	}

	@Override
	public String toString() {
		return INPUT_SELECT.COMPANAY + "," + this.getName() + "," + this.getPhoneNumber() + "," + company + "\n";
	}
}
