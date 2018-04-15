import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Farmer extends Users{
	private String address;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void BookSpace(Calendar cl) throws ClassNotFoundException
	{
		Scanner sc= new Scanner (System.in);
		Class.forName("org.postgresql.Driver");
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","roshan");
			System.out.println();
			System.out.println("Enter Warehouse ID");
			int id=sc.nextInt();
			System.out.println("Enter FROM Date in DDMM");
			int date1=sc.nextInt();
			System.out.println("Enter TO Date in DDMM");
			int date2=sc.nextInt();
			System.out.println("Enter Volume you want to book");
			int vol=sc.nextInt();
			
			int m1=date1%100;
			int m2=date2%100;
			int d1=date1/100;
			int d2=date2/100;
			
			if(m1==2)
				d1=d1+31;
			else if(m1==3)
				d1=d1+59;
			
			if(m2==2)
				d2=d2+31;
			else if(m2==3)
				d2=d2+59;
			
			Statement stmt=con.createStatement();
			String qry;
			qry = "Select * from warehouses where whid = '"+id+"';";
			ResultSet rs;
			rs=stmt.executeQuery(qry);
			int count=0;
			Warehouse wh = new Warehouse();
			wh.setWHid(id);
			while(rs.next()) {
				String addrs =rs.getString("location");
				int capacity=rs.getInt("capacity");
				String type=rs.getString("type");
				wh.setAddress(addrs);
				wh.setCapacity(capacity);
				wh.setType(type);
				count++;
			}
			rs.close();
			con.close();
			if(count==0)
				System.out.println("No Such Warehouse Found !");
			else
			{
				boolean status=true;
				for(int i=d1;i<=d2;i++)
				{
					if((cl.getbooking(id, i)+vol)>=wh.getCapacity())
					{
						status=false;
						System.out.println("Requsted Space not available for the current dates");
						break;
					}
				}
				if(status==true)
				{
					for(int i=d1;i<=d2;i++)
						cl.setbooking(id, i, vol);
					System.out.println("Booking Successfull!");
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Booking Failed. Try Again!");
		}
	}
	
	public void FindWarehouse() throws ClassNotFoundException
	{
		Class.forName("org.postgresql.Driver");
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","roshan");
		

		Statement stmt1=con.createStatement();
		String qry1,qry;
		qry1 = "Select * from warehouses;";
		qry = "Select count(*) from warehouses;";
		ResultSet rs1,rs;
		rs=stmt1.executeQuery(qry);
		int rowcount = 0;
		while(rs.next())
		{
			rowcount=rs.getInt("count");
		}
		rs.close();
		rs1=stmt1.executeQuery(qry1);
		Warehouse wh[] = new Warehouse[rowcount];
		int i=0;
		while(rs1.next()) {
			wh[i] = new Warehouse();
			int id =rs1.getInt("whid");
			String addrs =rs1.getString("location");
			int cap=rs1.getInt("capacity");
			String type=rs1.getString("type");
			wh[i].setWHid(id);
			wh[i].setAddress(addrs);
			wh[i].setCapacity(cap);
			wh[i].setType(type);
			i++;
		}
		
		rs1.close();
		
	
		Statement stmt2=con.createStatement();
		String qry2;
		qry = "Select count(*) from location;";
		qry2 = "Select * from location;";
		ResultSet rs2;
		rs=stmt2.executeQuery(qry);
		rowcount = 0;
		while(rs.next())
		{
			rowcount=rs.getInt("count");
		}
		rs.close();
		rs2=stmt2.executeQuery(qry2);
		Location lc[] = new Location[rowcount];
		i=0;
		while(rs2.next()) {
			lc[i] = new Location();
			String city =rs2.getString("city");
			int x =rs2.getInt("x");
			int y =rs2.getInt("y");
			lc[i].setCity(city);
			lc[i].setX(x);
			lc[i].setY(y);
			i++;
		}
		
		rs2.close();
		con.close();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your city: ");
		String loc = sc.nextLine();
		
		boolean warehouseAvailable = false;
		for(i=0;i<wh.length;i++)
		{
			if(loc.toLowerCase().equals(wh[i].getAddress().toLowerCase()))
			{
				System.out.println("Warehouse found in "+wh[i].getAddress() );
				System.out.println("Warehouse ID: "+wh[i].getWHid());
				System.out.println("Capacity: "+wh[i].getCapacity());
				System.out.println("Type: "+wh[i].getType());
				warehouseAvailable = true;
			}
		}
		
		if(!warehouseAvailable)
		{
			System.out.println("Sorry there are no warehouses available in this city. \nDo you want to find nearby warehouses?");
			System.out.print("Enter YES or NO: ");
			String choice = sc.nextLine();
			if(choice.toLowerCase().equals("yes"))
			{
				Location city1=new Location();
				Location city2=new Location();
				
				for(i=0;i<lc.length;i++)
				{
					if(loc.toLowerCase().equals(lc[i].getCity().toLowerCase()))
					{				
						city1=lc[i];
						break;
					}
				}
				
				
				System.out.println("Warehouses in 200km radius are: ");
				boolean whf=false;
				for(i=0;i<wh.length;i++)
				{
					String c = wh[i].getAddress();
					
					for(int j=0;j<lc.length;j++)
					{
						if(c.toLowerCase().equals(lc[j].getCity().toLowerCase()))
						{
							city2=lc[j];
							if(city1.calcDistance(city2) <= 200){
								System.out.println("There is warehouse available at " + c );
								System.out.println("Warehouse ID: "+wh[i].getWHid());
								System.out.println("Capacity: "+wh[i].getCapacity());
								System.out.println("Type: "+wh[i].getType());
								System.out.println();
								whf=true;
							}
							
						}
					}
				}
				if(whf==false)
				{
					System.out.println("Sorry No Warehouse Found" );
				}
			}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
