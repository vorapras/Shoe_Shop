package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import common.GlobalData;

public class UserManager {
	public static ArrayList<UserDB> getAllUser() {
		ArrayList<UserDB> list = new ArrayList<UserDB>();

		try {
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/" + GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "SELECT * FROM users";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String usertype = rs.getString("usertype");

				UserDB cc = new UserDB(id, username, password, usertype);
				list.add(cc);

				// print the results
				System.out.format("%d, %s, %s, %s\n", id, username, password, usertype);
			}
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}

	public static void saveNewUser(UserDB x) {
		try {
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/" + GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "INSERT INTO users VALUES(0, '" + x.username + "','" + x.password + "','" + x.usertype
					+ "' )";

			// create the java statement
			Statement st = conn.createStatement();

			// UpdateCustomer
			st.executeUpdate(query);
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}

	public static boolean checkLogin(String username, String password) {
//		try {
//			// create our mysql database connection
//			String myDriver = "org.gjt.mm.mysql.Driver";
//			String myUrl = "jdbc:mysql://localhost/" + GlobalData.DATABASE_DATABASE_NAME;
//
//			Class.forName(myDriver);
//			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
//					GlobalData.DATABASE_PASSWORD);
//
//			// our SQL SELECT query.
//			// if you only need a few columns, specify them by name instead of
//			// using "*"
//			String query = "SELECT * FROM users WHERE username = '"+username+"' AND password = '"+password+"'";
//            System.out.println(query);
//			// create the java statement
//			Statement st = conn.createStatement();
//
//			// execute the query, and get a java resultset
//			ResultSet rs = st.executeQuery(query);
//
//			// iterate through the java resultset
//			while (rs.next()) 
//			{
//			GlobalData.CurrentUser_userID = rs.getInt(1);
//			GlobalData.CurrentUser_username = rs.getString(2);
//			GlobalData.CurrentUser_usertype =rs.getString(3);
//			return true;
//			
//			}
//			st.close();
//		} catch (Exception e) {
//			System.err.println("Got an exception! ");
//			System.err.println(e.getMessage());
//		}
// PreparedStatements are the way to go, because they make SQL injection impossible. Here's a simple example taking the user's input as the parameters:		
		try {
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/" + GlobalData.DATABASE_DATABASE_NAME;

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "SELECT * FROM users WHERE username = ? AND password = ? ";
            System.out.println(query);
			// create the java statement
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
            st.setString(1,username);
            st.setString(2,password);
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery();

			// iterate through the java resultset
			while (rs.next()) 
			{
			GlobalData.CurrentUser_userID = rs.getInt(1);
			GlobalData.CurrentUser_username = rs.getString(2);
			GlobalData.CurrentUser_usertype =rs.getString(3);
			return true;
			
			}
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		return false;
	}
}
