package com.geekbrains.chat.server;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) {
        new MySQL();
        try {
            MySQL.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQL.disconnect();
        }
        new Server(8189); //запускаем работу сервера
    }
}
