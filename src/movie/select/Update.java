package movie.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import movie.sql.Conn;
import movie.util.Movie;

public class Update {

	
	public void normalClick(int id,int tid) {
		Movie m = new Select().getMovieById(id, tid);
		String sql = "update "+new Select().getTable(tid)+" set click = '"+(m.getClick()+1)+"' where id = '"+m.getId()+"'";
		String sql2 = "update "+new Select().getTable(tid)+" set down = '"+(m.getDown()+1)+"' where id = '"+m.getId()+"'";
		System.out.println(sql);
		System.out.println(sql2);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = Conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
			pstmt = con.prepareStatement(sql2);
			pstmt.execute();
			pstmt.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	
}
