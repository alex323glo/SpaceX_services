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

    private User target;

    public UserBuilder() {
        target = new User();
    }

    public UserBuilder email(String email) {
        if (email == null) {
            throw new NullPointerException("email is null");
        }
        target.setEmail(email);

        return this;
    }

    public UserBuilder password(String password) {
        if (password == null) {
            throw new NullPointerException("password is null");
        }
        target.setPassword(password);

        return this;
    }

    public UserBuilder userInfoMap(Map<InfoType, UserInfo> userInfoMap) {
        if (userInfoMap == null) {
            throw new NullPointerException("userInfoMap is null");
        }
        target.setUserInfoMap(userInfoMap);

        return this;
    }

    public UserBuilder info(UserInfo info) {
        if (info == null || info.getType() == null) {
            throw new NullPointerException("type or info is null");
        }
        target.getUserInfoMap().put(info.getType(), info);

        return this;
    }

    public User build() {
        User newUser = new User();
        newUser.setEmail(new String(target.getEmail()));
        newUser.setPassword(new String(target.getPassword()));
        newUser.setUserInfoMap(new HashMap<>(target.getUserInfoMap()));
        return target;
    }
}
