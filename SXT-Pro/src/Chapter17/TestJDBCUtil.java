package Chapter17;
import java.sql.*;

/**
 * Test The Usage Of JDBCUtil
 * Used With DB.properties
 * @author Woo
 *
 */
public class TestJDBCUtil {
	public static void main(String[] args) {
		Connection Con = null;
		PreparedStatement Ps = null;
		ResultSet Rs = null;
		try {
			// Con = JDBCUtil.getMySqlConnection01("mydb", "root", "root");
			Con = JDBCUtil.getMySqlConnection02();
			Ps = Con.prepareStatement("select * from user where id = ?");
			Ps.setInt(1, 5);
			Rs = Ps.executeQuery();
			while (Rs.next()) {
				System.out.println(Rs.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.Close(Con, Ps, Rs);
		}
	}
}
