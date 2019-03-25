package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	final String URL
    = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=JST";
    final String USER = "poker";
    final String PASS = "group8931";

    protected Connection conn = null;

    protected void open(){
    	 try {
    	        Class.forName("com.mysql.jdbc.Driver");
    	        conn = DriverManager.getConnection(URL, USER, PASS);
    	        if(conn==null){
    	        	System.out.println("conn==null");
    	        }
    	    } catch (SQLException | ClassNotFoundException e) {
    	        e.printStackTrace();
    	    }
    }

}
