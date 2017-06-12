package movie.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import movie.sql.Conn;
import movie.util.Movie;

public class Select {

	public List<Movie> getAllMovie() {
		
		return get80SByName("all movie");
	}

	public List<Movie> getMovieByClick(int num) {
		System.out.println("Click Num = " + num);
		return getMovieByTerm(Movie.byClick, num);
	}

	public List<Movie> getMovieByDownload(int num) {
		System.out.println("Down Num = " + num);
		return getMovieByTerm(Movie.byDown, num);
	}

	public List<Movie> getMovieByDate(int num) {

		System.out.println("Date Num = " + num);
		return getMovieByTerm(Movie.byDate, num);
	}

	public List<Movie> getMovieByType(String type, int num) {
		System.out.println("Type Num = " + num);
		String name = "";
		if (type.equals("dz")) {
			name = "动作";
		} else if (type.equals("xj")) {
			name = "喜剧";
		} else if (type.equals("kh")) {
			name = "科幻";
		} else if (type.equals("wx")) {
			name = "武侠";
		}

		List<Movie> list = get80SByType(name);
		if (num == 2) {
			list = list.subList(0, 20);
		} else {
			list = list.subList(20 + (num - 3), 20 + (num - 3) + 1);
		}
		return list;

	}

	public Movie getMovieById(int id, int tid) {
		String table = getTable(tid);
		String sql = "select * from " + table + " where id = '" + id + "'";

		Movie m = new Movie();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = Conn.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.first();
			m.setId(rs.getInt(1));
			m.setTid(rs.getInt(2));
			m.setName(rs.getString(3));
			m.setLink(rs.getString(4));
			m.setImage(rs.getString(5));
			m.setPic(rs.getString(6));
			m.setText(rs.getString(7));
			m.setType(rs.getString(8));
			m.setDate(rs.getString(9));
			m.setClick(rs.getInt(10));
			m.setDown(rs.getInt(11));

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


		if(m.getName()==null){
			return getAllMovie().get(0);
		}else{
			return m;
		}

	}
	

	public List<Movie> getMovieByName(String name) {

		List<Movie> list = new ArrayList<>();
		if(get80SByName(name).size()<=100){
			list.addAll(get80SByName(name));
		}else{
			list = get80SByName(name).subList(0, 100);
		}
		Collections.sort(list, Movie.byClick);
		return list;
	}

	private List<Movie> getMovieByTerm(Comparator<Movie> com, int num) {
		List<Movie> list = getAllMovie();

		Collections.sort(list, com);

		if (num == 1) {
			list = list.subList(0, 9);
		} else if (num == 2) {
			list = list.subList(0, 20);
		} else {
			list = list.subList(20 + (num - 3), 20 + (num - 3) + 1);
		}
		return list;
	}

	private List<Movie> get80SByType(String type) {
		List<Movie> list = new ArrayList<>();
		String sql = "select * from movie_80s where type like '%" + type + "%'";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = Conn.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Movie m = new Movie();
				m.setId(rs.getInt(1));
				m.setTid(rs.getInt(2));
				m.setName(rs.getString(3));
				m.setLink(rs.getString(4));
				m.setImage(rs.getString(5));
				m.setPic(rs.getString(6));
				m.setText(rs.getString(7));
				m.setType(rs.getString(8));
				m.setDate(rs.getString(9));
				m.setClick(rs.getInt(10));
				m.setDown(rs.getInt(11));

				if (m.getName() != null && !m.getName().isEmpty())
					list.add(m);
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		Collections.sort(list, Movie.byClick);

		return list;
	}

	private List<Movie> get80SByName(String name) {

		List<Movie> list = new ArrayList<>();
		String sql = "";
		if (name.equals("all movie")) {
			sql = "select * from movie_80s";
		} else {
			sql = "select * from movie_80s where name like '%" + name + "%'";
		}
		
		if(name ==""){
			return list;
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = Conn.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Movie m = new Movie();
				m.setId(rs.getInt(1));
				m.setTid(rs.getInt(2));
				m.setName(rs.getString(3));
				m.setLink(rs.getString(4));
				m.setImage(rs.getString(5));
				m.setPic(rs.getString(6));
				m.setText(rs.getString(7));
				m.setType(rs.getString(8));
				m.setDate(rs.getString(9));
				m.setClick(rs.getInt(10));
				m.setDown(rs.getInt(11));

				if (m.getName() != null && !m.getName().isEmpty())
					list.add(m);
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(list, Movie.byClick);

		return list;
	}

	public String getTable(int tid) {

		if (tid == 1)
			return "movie_80s";

		return "";
	}
}
