package movie.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

	public static Connection getConnection() {
		Connection connection = null;
		String driver = Server.DRIVER;
		String url = Server.URL;
		String user = Server.USER;
		String pass = Server.PASS;

		try {

			Class.forName(driver);
			connection = (Connection) DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

}
