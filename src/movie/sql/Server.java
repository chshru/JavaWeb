package movie.sql;




class Server {

	public static String URL = "jdbc:mysql://localhost:3306/javaweb?useUnicode=true&characterEncoding=utf-8";
	public static String DRIVER = "com.mysql.jdbc.Driver";
	public static String USER = "root";
	public static String PASS = "********";
	
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUseUnicode() {
		return useUnicode;
	}

	public void setUseUnicode(String useUnicode) {
		this.useUnicode = useUnicode;
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String host;
	private String database;
	private String useUnicode;
	private String characterEncoding;
	private String user;
	private String password;
	private String driver;
	private String url;
	private static Server server;

	
	private Server() {
	}

	public static Server GET() {
		if (server != null) {
			return server;
		}
		return new Server();
	}


}
