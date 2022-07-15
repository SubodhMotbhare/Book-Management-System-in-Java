import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class connection {
	
		Connection con = null;
		java.sql.PreparedStatement pst;
		public static Connection dbconnect()
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/book management","root","");
				return conn;
				
			}
			catch (Exception e2) {
				System.out.println(e2);
				return null;
			}
		}
		

	}  

	

