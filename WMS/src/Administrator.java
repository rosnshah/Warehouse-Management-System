import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Administrator extends Users{

	public void RemoveWarehouse() throws ClassNotFoundException
	{
		Scanner sc= new Scanner (System.in);
		Class.forName("org.postgresql.Driver");
		Connection con;
		try {
				int choice=0;
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","roshan");
				Statement stmt=con.createStatement();
				String S;
				while(true)
				{
					System.out.println("1. Delete by Name:");
					System.out.println("2. Delete by Warehouse ID");
					System.out.print("Enter Your Choice : ");
					choice =sc.nextInt();
					sc.nextLine();
					if(choice==1)
					{
						System.out.print("Enter Warehouse name which you want to remove: ");
						String name = sc.nextLine();
						S="delete from warehouses where location='"+name+"';";
						stmt.executeUpdate(S);
						System.out.println("Record Successfully Deleted!");
						con.close();
						break;
					}
					else if(choice==2)
					{
						System.out.print("Enter Warehouse ID which you want to remove: ");
						int id = sc.nextInt();
						S="delete from warehouses where whid='"+id+"';";
						stmt.executeUpdate(S);
						System.out.println("Record Successfully Deleted!");
						con.close();
						break;
					}
					else
					{
						System.out.println("Invalid Option. Try Again!");
					}
				}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Attempt Failed. Try Again!");
		}
	}
	
	public void addWarehouse() throws ClassNotFoundException
	{
		Scanner sc= new Scanner (System.in);
		Class.forName("org.postgresql.Driver");
		Connection con;
		try {
				int choice=0;
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","roshan");
				Statement stmt=con.createStatement();
				String S;
				System.out.print("Enter Warehouse Location : ");
				String loc =sc.nextLine();
				System.out.print("Enter Warehouse ID : ");
				int id =sc.nextInt();
				System.out.print("Enter Warehouse Capacity : ");
				int cap =sc.nextInt();
				System.out.print("Enter Warehouse Type : \n 1. Cold \n 2. Bonded \n 3. Non-Bonded \n");
				String type="";int k;
				k=sc.nextInt();
				if(k==1)
					type="Cold";
				else if(k==2)
					type="Bonded";
				else if(k==3)
					type="Non-Bonded";
				S = "insert into warehouses values('"+id+"','"+loc+"','"+cap+"','"+type+"');";
				stmt.executeUpdate(S);
				System.out.println("Warehouse Successully Added!");
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Attempt Failed. Try Again!");
		}
	}
}
