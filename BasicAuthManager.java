package com.geekbrains.chat.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BasicAuthManager implements AuthManager {
    private class Entry {
        private String login;
        private String password;
        private String nickname;

        public Entry(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }


    //private List<Entry> users;

//    public BasicAuthManager() {
//        this.users = new ArrayList<>();
//        users.add(new Entry("login1", "pass1", "user1"));
//        users.add(new Entry("login2", "pass2", "user2"));
//        users.add(new Entry("login3", "pass3", "user3"));
//    }

    @Override
    public String getNickNameByLoginAndPassword(String login, String password) throws SQLException { //метод аутентификации по логину и паролю
        Connection connection = null;
        PreparedStatement ps = connection.prepareStatement("SELECT FROM login (login, passwort, nickname) values (?. ?, ?)");
        ps.setString(2, "login");
        ps.setString(3, "password");
        ps.setString(4, "nickname");
        int i = ps.executeUpdate();
        if(ps.setString(1, "login");)equals(login) && ps.setString(2, "password");)equals(password){
            return ps.setString(3, "nickmane");
        
    }return null;
}
}
