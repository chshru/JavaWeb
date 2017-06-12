package movie.util;

import java.util.Comparator;

public class Movie {
	

	private int id;
	private int tid;
	private int click;
	private int down;
	private String name;
	private String link;
	private String image;
	private String pic;
	private String text;
	private String type;
	private String date;
	
	
	public static Comparator<Movie> byDown = new Comparator<Movie>() {
		@Override
		public int compare(Movie o2, Movie o1) {
			return o1.getDown() - o2.getDown();
		}
	};
	public static Comparator<Movie> byClick = new Comparator<Movie>() {
		@Override
		public int compare(Movie o2, Movie o1) {
			return o1.getClick() - o2.getClick();
		}
	};
	public static Comparator<Movie> byDate = new Comparator<Movie>() {
		@Override
		public int compare(Movie o2, Movie o1) {
			String date1 = o1.getDate();
			String date2 = o2.getDate();
			if (!date1.equals(date2)) {
				return date1.compareTo(date2);
			}
			return o1.getClick() - o2.getClick();
		}
	};
	
	
	
	public int getDown() {
		return down;
	}
	public void setDown(int down) {
		this.down = down;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}

