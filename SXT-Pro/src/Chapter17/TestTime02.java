package Chapter17;
import java.sql.*;
import java.text.*;

/**
 * Test The Time(java.sql.Time, Date, Timestamp)
 * Get the data from the designated time slot, based on TestTime01
 * @author Woo
 *
 */
public class TestTime02 {
	// convert the string standing for a certain date into a long number
	// the format of the string(yyyy-MM-dd hh:mm:ss)
	// use DateFormat
	public static long StringToTimestamp(String TimeStampString) {
		DateFormat Df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return Df.parse(TimeStampString).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// similar logic with the method above
	public static long StringToDate(String DateString) {
		DateFormat Df = new SimpleDateFormat("yyyy-mm-dd");
		try {
			return Df.parse(DateString).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void main(String[] args) {
		Connection Con = null;
		PreparedStatement Ps01 = null;
		PreparedStatement Ps02 = null;
		ResultSet Rs01 = null;
		ResultSet Rs02 = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "root");
			
			// defined the time slot of the regtime(Timestamp)
			Ps01 = Con.prepareStatement("select * from user where regtime > ? and regtime < ? order by regtime");
			// the format of the parameter string must match up with the method
			// do not use Date to fetch the regtime
			Timestamp Start = new Timestamp(StringToTimestamp("2021-1-10 10:08:10"));
			Timestamp End = new Timestamp(StringToTimestamp("2021-1-10 13:00:10"));
			Ps01.setObject(1, Start); // first ?
			Ps01.setObject(2, End); // second ?
			Rs01 = Ps01.executeQuery();
			// print out the result
			// you can also use the index to get corresponding value
			while (Rs01.next()) {
				// use key word to get corresponding value
				System.out.println(Rs01.getInt("id") + ", " + Rs01.getString("username") + ", " + Rs01.getTimestamp("regtime"));
			}
			
			// defined the date slot of the latestOnline(Date)
			Ps02 = Con.prepareStatement("select * from user where latestOnline > ? and latestOnline < ? order by latestOnline");
			// Use Date to get latestOnline
			Date Head = new java.sql.Date(StringToDate("2021-1-10"));
			Date Tail = new java.sql.Date(StringToDate("2021-1-12"));
			Ps02.setObject(1, Head);
			Ps02.setObject(2, Tail);
			Rs02 = Ps02.executeQuery();
			System.out.println();
			while (Rs02.next()) {
				System.out.println(Rs02.getInt(1) + ", " + Rs02.getString(2) + ", " + Rs02.getDate(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Rs02 != null) {
				try {
					Rs02.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (Rs01 != null) {
				try {
					Rs01.close();
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
