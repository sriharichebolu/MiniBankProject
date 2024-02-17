package com.jsp.bank;

import java.util.Scanner;

public class Main {
public static void main(String[] args) {
	Scanner scan=new Scanner(System.in);
	BankDao b=new BankDaoImp1();
	System.out.println("Enter Any One Of The Operation");
	System.out.println("1.Credit               2.debit");
	System.out.println("3.check                4.Mobile Transcation");
	System.out.println("5.Changepin");
	int n=scan.nextInt();
	switch(n) {
	case 1:
		b.credit();
	break;
	case 2:b.debit();
	break;
	case 3:b.balancecheck();
	break;
	case 4:b.mobileTransaction();
	break;
	case 5:b.changePin();
	break;
	default:
		System.out.println("Invalid Details2");
		
	}
}
}
