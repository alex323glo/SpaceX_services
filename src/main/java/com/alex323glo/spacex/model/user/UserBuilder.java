package com.alex323glo.spacex.model.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for User model.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see User
 */
public class UserBuilder {

    private String targetEmail;
    private String targetPassword;
    private Map<InfoType, UserInfo> targetUserInfoMap;

    public UserBuilder() {
        targetUserInfoMap = new HashMap<>();
    }

    public UserBuilder email(String email) {
        if (email == null) {
            throw new NullPointerException("email is null");
        }
        targetEmail = email;

        return this;
    }

    public UserBuilder password(String password) {
        if (password == null) {
            throw new NullPointerException("password is null");
        }
        targetPassword = password;

        return this;
    }

    public UserBuilder userInfoMap(Map<InfoType, UserInfo> userInfoMap) {
        if (userInfoMap == null) {
            throw new NullPointerException("userInfoMap is null");
        }
        targetUserInfoMap = userInfoMap;

        return this;
    }

    public UserBuilder info(UserInfo info) {
        if (info == null || info.getType() == null) {
            throw new NullPointerException("type or info is null");
        }
        targetUserInfoMap.put(info.getType(), info);

        return this;
    }

    public User build() {
        User newUser = new User();
        newUser.setEmail(targetEmail == null ? null : new String(targetEmail));
        newUser.setPassword(targetPassword == null ? null : new String(targetPassword));
        newUser.setUserInfoMap(new HashMap<>(targetUserInfoMap));
        return newUser;
    }
}
