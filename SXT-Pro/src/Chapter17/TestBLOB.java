package Chapter17;
import java.sql.*;
import java.io.*;

/**
 * Test The Usage Of BLOB, BLOB: Binary Large Object
 * Used to store the large size binary data(Video, Picture). Large Object is different, 
 * different database has different ways to deal with it. Large Object is different from 
 * the normal object, the operations toward large object is processed in the way of stream,
 * unlike the object which can be read out immediately.
 * @author Woo
 *
 */
public class TestBLOB {
	public static void main(String[] args) {
		Connection Con = null;
		PreparedStatement Ps = null;
		ResultSet Rs = null;
		InputStream Is = null; // BLOB is in binary format, can't be used with reader
		OutputStream Os = null; // write the BLOB object in to a file
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "root");
			
			// write in with BLOB
			/*
				Ps = Con.prepareStatement("update Product set Picture = ? where id = 2");
				// use FileInputStream to store the picture in to the database
				Ps.setBlob(1, new FileInputStream("Text/Cat.jfif"));
				Ps.execute();
			*/
			
			Ps = Con.prepareStatement("select * from Product");
			// read out with BLOB
			Rs = Ps.executeQuery();
			while (Rs.next()) {
				Blob B = Rs.getBlob("Picture"); // using index also works
				Is = B.getBinaryStream();
				Os = new FileOutputStream("Text/Meow.jfif");
				byte[] Flush = new byte[1024];
				int Len = 0;
				while (Len != -1) {
					Len = Is.read(Flush);
					if (Len == -1) {
						break;
					}
					Os.write(Flush, 0 , Len);
					Os.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Os != null) {
				try {
					Os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (Is != null) {
				try {
					Is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (Ps != null) {
				try {
					Ps.close();
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
