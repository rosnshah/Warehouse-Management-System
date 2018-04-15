import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Users {
	
	private String username;
	private String name;
	private String password;
	private String contact;
	private String emailid;
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String usr) {
		this.username = usr;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String cntct) {
		this.contact = cntct;
	}
	public String getemail() {
		return emailid;
	}
	public void setemail(String eml) {
		this.emailid = eml;
	}
	public String getType() {
		return type;
	}
	public void setType(String tp) {
		this.type = tp;
	}

	public void SignUp() throws ClassNotFoundException {
		Scanner sc= new Scanner (System.in);
		Class.forName("org.postgresql.Driver");
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","roshan");
			System.out.println("SignUp for Amazon Warehouses to get excellent Warehouse Management Services");
			System.out.print("Enter Your Name:");
			String name = sc.nextLine();
			System.out.print("Enter Username:");
			String usrname = sc.nextLine();
			System.out.print("Enter password:");
			String pwd = sc.nextLine();
			System.out.print("Enter email:");
			String email = sc.nextLine();
			System.out.print("Enter Contact:");
			String cntct = sc.nextLine();
			System.out.print("Enter F if farmer or B if Buyer:");
			String type = sc.nextLine();
			type.toUpperCase();
			Statement stmt=con.createStatement();
			String S;
			S = "insert into users values('"+usrname+"','"+pwd+"','"+name+"','"+email+"','"+cntct+"','"+type+"');";
			stmt.executeUpdate(S);
			System.out.println("User Successully Registered!");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Registration Failed. Try Again!");
		}
	}
	
	
	
	public boolean Login() throws ClassNotFoundException
	{	
		boolean status=true;
		Scanner sc=new Scanner(System.in);
		Class.forName("org.postgresql.Driver");
		Connection con;
		
			try {
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","roshan");
				System.out.println();
				System.out.println("Login to Amazon Warehouses!");
				System.out.print("Enter Username:");
				String usrname = sc.nextLine();
				System.out.print("Enter password:");
				String pwd = sc.nextLine();
				Statement stmt=con.createStatement();
				String qry;
				qry = "Select * from users where username = '"+usrname+"' and password = '"+pwd+"';";
				ResultSet rs;
				rs=stmt.executeQuery(qry);
				int count=0;
				this.setUsername(usrname);
				this.setPassword(pwd);
				while(rs.next()) {
					String name=rs.getString("name");
					String email=rs.getString("email");
					String cntct=rs.getString("contact");
					String type=rs.getString("type");
					this.setName(name);
					this.setContact(cntct);
					this.setemail(email);
					this.setType(type);
					count++;
				}
				
				if(count==0)
					{System.out.println();
					System.out.println("Invalid Credentials .Try Again!");
					status=false;
					}
				else
					System.out.println("Login Successful !");
				rs.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Login Failed!");
				status=false;
			}
			return status;
		
	}
	
	
	
}
