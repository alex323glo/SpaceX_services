package com.alex323glo.spacex.model.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Model of user in this application.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class User {
    private String email;
    private String password;
    private Map<InfoType, UserInfo> userInfoMap;

    public User() {
        userInfoMap = new HashMap<>();
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        userInfoMap = new HashMap<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<InfoType, UserInfo> getUserInfoMap() {
        return userInfoMap;
    }

    public void setUserInfoMap(Map<InfoType, UserInfo> userInfoMap) {
        this.userInfoMap = userInfoMap;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!email.equals(user.email)) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return userInfoMap != null ? userInfoMap.equals(user.userInfoMap) : user.userInfoMap == null;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
