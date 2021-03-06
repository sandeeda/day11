package com.capgemini.bankapp.util;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:db.properties")
public class DbUtil {

/*
	@Autowired
	private Environment env;*/
	
	
	@Value("${DB_URL}")
	private String DB_URL;

	@Value("${uname}")
	private String uname;

	@Value("${password}")
	private String password;
	

/*
	private String getDbUrl()
	{
		return env.getProperty("DB_URL");
	}
	private String getUsername()
	{
		return env.getProperty("uname");
	}
	private String getPassword()
	{
		return env.getProperty("password");
	}
*/	
/*
	private static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
	private static final String username="root";
	private static final String password="root";
*/

	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("i am here");
			System.out.println(DB_URL);
			System.out.println(uname);
			System.out.println(password);
			connection = DriverManager.getConnection(DB_URL, uname, password);
			System.out.println("connected");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
