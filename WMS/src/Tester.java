import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.sql.*;

public class Tester {

	public static void main(String[] args) throws ClassNotFoundException  {
		// TODO Auto-generated method stub
		
		Calendar cl = new Calendar();
		System.out.println("Welcome to Amazon Warehouses !!!");
		while(true)
		{	
			Scanner sc= new Scanner (System.in);
			System.out.println();
			System.out.println("1. Sign Up");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println();
			System.out.print("Enter Your Choice : ");
			int opt =sc.nextInt();
			int choice;
			sc.nextLine();
			if(opt==1)
			{//SignUp
				Users usr = new Users();
				usr.SignUp();
			}
			else if (opt==2)
			{//Login
				Users usr = new Users();
				boolean var=usr.Login();
				if(var==false)
					continue;
				if(usr.getType().equals("A"))
				{
					Administrator admin = new Administrator();
					admin.setName(usr.getName());
					admin.setUsername(usr.getUsername());
					admin.setPassword(usr.getPassword());
					admin.setType(usr.getType());
					admin.setemail(usr.getemail());
					admin.setContact(usr.getContact());
					while(true)
					{
						System.out.println();
						System.out.println("Welcome "+usr.getName());
						System.out.println("1. Add Warehouse");
						System.out.println("2. Remove Warehouse");
						System.out.println("3. Logout");
						System.out.println();
						System.out.print("Enter Your Choice : ");
						choice =sc.nextInt();
						if(choice==1)
						{
							admin.addWarehouse();
						}
						else if(choice==2)
						{
							admin.RemoveWarehouse();
						}
						else if(choice==3)
						{	
							System.out.println("Logged Out Successfully!");
							break;
						}
						else
						{
							System.out.println("Invalid Option. Try Again!");
						}
					}
				}
				else if(usr.getType().equals("F"))
				{
					Farmer fr=new Farmer();
					fr.setName(usr.getName());
					fr.setUsername(usr.getUsername());
					fr.setPassword(usr.getPassword());
					fr.setType(usr.getType());
					fr.setemail(usr.getemail());
					fr.setContact(usr.getContact());
					while(true)
					{
						System.out.println();
						System.out.println("Welcome "+usr.getName());
						System.out.println("1. Find Warehouse");
						System.out.println("2. Reserve Space");
						System.out.println("3. Logout");
						System.out.println();
						System.out.print("Enter Your Choice : ");
						choice =sc.nextInt();
						if(choice==1)
						{
							fr.FindWarehouse();
						}
						else if(choice==2)
						{
							System.out.println("Today's date: 1st January 2018. Enter date from the next 3 months");
							fr.BookSpace(cl);
						}
						else if(choice==3)
						{
							System.out.println("Logged Out Successfully!");
							break;
						}
						else
						{
							System.out.println("Invalid Option. Try Again!");
						}
					}
				}
				else if(usr.getType().equals("B"))
				{
					while(true)
					{
						System.out.println();
						System.out.println("Welcome "+usr.getName());
						System.out.println("1. Buy Products");
						System.out.println("2. Logout");
						System.out.println();
						System.out.print("Enter Your Choice : ");
						choice =sc.nextInt();
						if(choice==1)
						{
							
						}
							else if(choice==2)
						{
							break;
						}
						else
						{
							System.out.println("Invalid Option. Try Again!");
						}
					}
				}
			}
			else if(opt==3)
			{	
				System.out.println();
				System.out.println("Thank you for using our Services :)");
				System.exit(0);
			}
			else
			{	
				System.out.println();
				System.out.println("Invalid Option. Try Again!");
			}
		}

}
}
