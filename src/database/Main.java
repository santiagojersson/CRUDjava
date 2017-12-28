package database;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		insert("Chandler", "Bing");
	}
	
	
	public static ArrayList<String> get() throws Exception{
		try {
			Connection con= getConnection();
			PreparedStatement statement= con.prepareStatement("SELECT firstName, lastName FROM prueba");
			ResultSet result= statement.executeQuery();
			ArrayList<String> lista= new ArrayList<>();
			while(result.next()){
				System.out.println(result.getString("firstName"));
				System.out.print(" ");
				System.out.println(result.getString("lastName"));
				lista.add(result.getString("lastName"));
			}
			return lista;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static void update(int ID, String newValue) throws Exception{
		try {
			Connection con= getConnection();
			PreparedStatement statement= con.prepareStatement("UPDATE prueba SET lastName=? WHERE id= ?");
			
			statement.setString(1, newValue);
			statement.setInt(2, ID);
			statement.executeUpdate();
			System.out.println("Se actualizó correctamente");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static void delete(int ID) throws Exception{
		try {
			Connection con= getConnection();
			PreparedStatement statement= con.prepareStatement("DELETE FROM prueba  WHERE id= ?");
			statement.setInt(1, ID);
			statement.executeUpdate();
			System.out.println("Se elimino correctamente");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	
	
	
	
	public static void insert(String v1, String v2) throws Exception{
		final String var1= v1;
		final String var2= v2;
		try {
			Connection con= getConnection();
			PreparedStatement posted= con.prepareStatement("INSERT INTO prueba(firstName,lastName)"
					+ "VALUES (?, ?)");
			posted.setString(1, var1);
			posted.setString(2, var2);
			posted.executeUpdate();
			System.out.println("Se inserto el valor");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void createTable(){
		try {
			Connection con= getConnection();
			PreparedStatement create= con.prepareStatement("CREATE TABLE IF NOT EXISTS prueba("
					+ "id int NOT NULL AUTO_INCREMENT PRIMARY KEY," 
					+ "firstName varchar(255)," 
					+ "lastName varchar(255))");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			System.out.println("Table has been created");
		}
		
	}
	
	
	public static Connection getConnection() throws Exception {
		try {
			String driver="com.mysql.jdbc.Driver";
			String dbName= "test";
			String url= "jdbc:mysql://localhost:3306/"+dbName;
			String userName= "root";
			String password = "root";
			Class.forName(driver);
			
			Connection con= DriverManager.getConnection(url,userName,password);
			System.out.println("Connected");
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return  null;
	}
	
}
