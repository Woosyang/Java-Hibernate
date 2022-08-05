package Chapter17;
import java.io.*;
import java.sql.*;

/**
 * Test The Usage Of CLOB, CLOB: Character Large Object
 * Put The String Or Text File Into CLOB Field Of The Database
 * Get The Corresponding Content Of From The Matched CLOB In Database  
 * CLOB is used to store the big size text data. Large Object is different, different
 * database has different ways to deal with it. Large Object is different from the 
 * normal object, the operations toward large object is processed in the way of stream,
 * unlike the object which can be read out immediately.
 * @author Woo
 *
 */
public class TestCLOB {
	public static void main(String[] args) {
		Connection Con = null;
		PreparedStatement Ps01 = null;
		PreparedStatement Ps02 = null;
		ResultSet Rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "root");
			Ps01 = Con.prepareStatement("insert into product (Name, Pwd, RegTime, Info) values (?, ?, ?, ?)");
			Ps01.setString(1, "Apple");
			Ps01.setObject(2, "asdfg");
			Ps01.setTimestamp(3, new Timestamp(1000345));
			// Clob use I/O Stream, write the text file into the database
			Ps01.setClob(4, new FileReader(new File("Text/CD.txt")));
			// write a string into the CLOB of the database using I/O stream
			// String -> byte[] -> char stream
			Ps01.setClob(4, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("Hello".getBytes()))));
			Ps01.executeUpdate();
			
			// search the CLOB object in the database and print it out in the console
			Ps02 = Con.prepareStatement("select * from product where id = ?");
			Ps02.setObject(1, 2); // get the CLOB from the row whose id = 2
			Rs = Ps02.executeQuery();
			while (Rs.next()) {
				Clob C = Rs.getClob("Info"); // get the CLOB
				Reader R = C.getCharacterStream(); // input stream
				int Temp = 0;
				while (Temp != -1) {
					Temp = R.read();
					if (Temp == -1) {
						break;
					}
					System.out.print((char) Temp);
				}
				R.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Rs != null) {
				try {
					Rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (Ps02 != null) {
				try {
					Ps02.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (Ps01 != null) {
				try {
					Ps01.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (Con != null) {
				try {
					Con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
