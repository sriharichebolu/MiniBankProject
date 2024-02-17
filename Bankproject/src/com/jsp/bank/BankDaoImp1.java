package com.jsp.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class BankDaoImp1 implements BankDao {

	@Override
	public void credit() {
		
		Scanner scan=new Scanner(System.in);
		double damount;
		boolean status=true;
		while(status) {
			System.out.println("Enter Your 10 digits of mobile Number");
			String mobilenumber=scan.next();
			if (mobilenumber.length()==10) {
				status=false;
				boolean statusp=true;
				int count=0;
				while(statusp){
					System.out.println("Enter Your Password");
					String password=scan.next();
					if(password.length()==5) {
						statusp=false;
						String url="jdbc:mysql://localhost:3306/teca44?user=root&passwrod=12345";
						String selectm="select * from bank where mob_number=?";
						String selectp="select * from bank where paassword=?";
						String select="select * from bank where mob_number=? and paassword=?";
						
						try {
							Connection connection=DriverManager.getConnection(url);
							PreparedStatement ps=connection.prepareStatement(selectm);
							ps.setString(1, mobilenumber);
							PreparedStatement ps1=connection.prepareStatement(selectp);
							ps1.setString(1,password);
							PreparedStatement ps2=connection.prepareStatement(select);
							ps2.setString(1, mobilenumber);
							ps2.setString(2, password);
							
							ResultSet rs=ps.executeQuery();
							ResultSet rs1=ps.executeQuery();
							ResultSet rs2=ps.executeQuery();
							if (rs.next()) {
								
								if(rs1.next()) {
									
									if(rs2.next()) {
										
										boolean otp1=true;
										int countotp;
										while(otp1) {
											Random r=new Random();
											int otp=r.nextInt(10000);
											if(otp>1000) {
												otp+=1000;
											}
											System.out.println("Your OTP is :"+otp);
											System.out.println("Enter Your OTP");
											int uotp=scan.nextInt();
											if(uotp==otp) {
												otp1=false;
												damount=rs2.getDouble(5);
												boolean wrongamount=true;
												while(wrongamount) {
													System.out.println("Enter your amount");
													double uamount=scan.nextDouble();
													if(uamount>0) {
														wrongamount=false;
														double add=damount+uamount;
														String update="update bank set amount=? where mob_number=?";
														PreparedStatement psu=connection.prepareStatement(update);
														psu.setDouble(1,add);
														psu.setString(2, mobilenumber);
														int num=psu.executeUpdate();
														if(num>0) {
															System.out.println("Amount Crited Successfully....!!");
														}
														else
														{
															System.out.println("not");
														}
													}
													else
													{
														System.out.println("Invalid Amount");
														wrongamount=true;
													}
												}
												
											}
											else
											{
												System.out.println("Invalid otp");
												otp1=true;
											}
										}
										
									}
									else
									{
										System.out.println("Invalid Details");
										status=true;
									}
								}
								else
								{
									System.out.println("Invalid Password");
									statusp=true;
									
								}
							}
							else
							{
								System.out.println("Invalid Details");
								status=true;
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else
					{
						System.out.println("re-enter Your password");
						statusp=true;
						count++;
					}
					if(count==3) {
						System.out.println("Login Again");
						statusp=false;
						status=true;
					}
				}
			} 
			else {
                   System.out.println("Invalid number");
                   status=true;
			}
		}

	}
	@Override
	public void debit() {
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Your Mobile Number");
		String mobilenumber=scan.next();
		System.out.println("Enter Your Password");
		String password=scan.next();
		
		String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
		
		String select="select * from bank where mob_number=? and paassword=?";
 
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, mobilenumber);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			
			 if(rs.next())
			 {
			     System.out.println("Enter The Amount");
			     Double uamount=scan.nextDouble();
			     if(uamount>0)
			     {
			    	 double damount=rs.getDouble(5);
			    	 if(damount>=uamount)
			    	 {
			    		 double sub=damount-uamount;
			    		 String update="update bank set amount=? where mob_number=?";
			    		 PreparedStatement ps1=connection.prepareStatement(update);
			    		 ps1.setDouble(1,sub);
			    		 ps1.setString(2, mobilenumber);
			    		 int result=ps1.executeUpdate();
			    		 if(result>0)
			    		 {
			    			 System.out.println("Amount Debited Successfull....!!");
			    		 }
			    		 else
			    		 {
			    			 System.out.println("server Down");
			    		 }
			    	 }
			    	 else
			    	 {
			    		 System.out.println("Insufficent Amount");
			    	 }
			     }
			     else
			     {
			    	 System.out.println("invalid amount");
			     }
			 }
			 else
			 {
				 System.out.println("Invalid details");
			 }
		} 
			 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mobileTransaction() {
		
		Scanner scan=new Scanner(System.in);
		boolean statusm=true;
		while(statusm) {
			System.out.println("Enter Your Mobile Number");
			String mobilenumber=scan.next();
			if(mobilenumber.length()==10) {
				statusm=false;
				boolean statusp=true;
				while(statusp) {
					System.out.println("Enter Your Password");
					String password=scan.next();
					if(password.length()==5) {
						statusp=false;
						String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
						String select="select * from bank where mob_number=? and paassword=?";
						try {
						  Connection connection = DriverManager.getConnection(url);
							PreparedStatement ps=connection.prepareStatement(select);
							ps.setString(1, mobilenumber);
							ps.setString(2, password);
							ResultSet rs=ps.executeQuery();
							if(rs.next())
							{
								double sdamount=rs.getDouble(5);
								System.out.println("Enter Recivers mobile number");
								String rmobilenumber=scan.next();
								String selectr="select * from bank where mob_number=?";
								PreparedStatement psr=connection.prepareStatement(selectr);
								psr.setString(1, rmobilenumber);
								ResultSet rsr=psr.executeQuery();
								if(rsr.next()) {
									double rdamount=rsr.getDouble(5);
									System.out.println("Enter amount to send");
									double sendamount=scan.nextDouble();
									if(sendamount>0) {
										
										if(sdamount>0) {
											double add=rdamount+sendamount;
											double sub=sdamount-sendamount;
											String updates="update bank set amount=? where mob_number=?";
											String updater="update bank set amount=? where mob_number=?";
											PreparedStatement psus=connection.prepareStatement(updates);
											psus.setDouble(1, sub);
											psus.setString(2, mobilenumber);
											int result=psus.executeUpdate();
											if(result>0)
											{
												System.out.println("Transcation Successfull...!!");
												PreparedStatement psur=connection.prepareStatement(updater);
												psur.setDouble(1, add);
												psur.setString(2, rmobilenumber);
												int result1=psur.executeUpdate();
												if (result1>0) {
													
													System.out.println("Amount Crited");
													
												} else {
                                                     System.out.println("Server busy");
												}
											}
											else
											{
												System.out.println("Processing");
											}
										}
										else
										{
											System.out.println("Insufficent Balance");
											
										}
									}else
									{
										System.out.println("");
									}
									
								}
								else
								{
									System.out.println("Invite your Amount To send");
								}
							}
							else
							{
								System.out.println("Invalid Details");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					else
					{
						System.out.println("Invalid Password");
						statusp=true;
					}
				}
			}
			else
			{
				System.out.println("Invalid Mobile Number");
				statusm=true;
			}
		}
		

	}

	@Override
	public void balancecheck() {

		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Your Mobile Number");
		String mobilenumber=scan.next();
		System.out.println("Enter Your Password");
		String password=scan.next();
		
		String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
		
		String select="select * from bank where mob_number=? and paassword=?";
        
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, mobilenumber);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				System.out.println("ID:TECA44"+rs.getInt(1));
				System.out.println("Name:"+rs.getString(2));
				String s1=rs.getString(3);
				String s2="";
				for(int i=0;i<s1.length();i++)
				{
					if(i<=1||i>=8)
					{
						s2+=s1.charAt(i);
					}
					else
					{
						s2=s2+"*";
					}
				}
				System.out.println("mobilenumber:"+s2);
				System.out.println("Balance:"+rs.getDouble(5));
			}
			else
			{
				System.out.println("Enter Valid Detail id check");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void changePin() {
		// TODO Auto-generated method stub
        String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
        String query="select * from bank where mob_number=?";
        
        try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			Scanner scan=new Scanner(System.in);
			System.out.println("Enter Your number");
			String number=scan.next();
			ps.setString(1, number);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				Random r=new Random();
				int otp=r.nextInt(10000); 
				if(otp<1000)
				{
					otp+=1000;
				}
				System.out.println("Your otp is:"+otp);
				System.out.println("Enter Your otp:");
				int uotp=scan.nextInt();
				if(otp==uotp)
				{
					System.out.println("Enter Your new Pin:");
					String np=scan.next();
					System.out.println("confirm Your pin:");
					String cp=scan.next();
					if(np.equals(cp))
					{
						String update="update bank set paassword=? where mob_number=?";
						PreparedStatement ps1=connection.prepareStatement(update);
						ps1.setString(1, np);
						ps1.setString(2, number);
						int num=ps1.executeUpdate();
						if(num>0)
						{
							System.out.println("pin update successfull.....");
						}
						else
						{
							System.out.println("Server problem");
						}
					}
					else
					{
						System.out.println("Password Mismatch");
					}
				}
				else
				{
					System.out.println("Invalid otp");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
