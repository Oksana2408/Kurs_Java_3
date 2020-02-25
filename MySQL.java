package com.geekbrains.chat.server;

import java.sql.*;

public class MySQL {
    private static Connection connection;
    static Statement stmt;

    public static void connect() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.JDBC.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
            stmt = connection.createStatement();
    }

    public static void disconnect(){
        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
