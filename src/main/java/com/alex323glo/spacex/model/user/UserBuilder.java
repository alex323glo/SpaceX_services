package com.alex323glo.spacex.model.user;

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

    public UserBuilder userInfoMap(Map<UserInfoType, UserInfo> userInfoMap) {
        if (userInfoMap == null) {
            throw new NullPointerException("userInfoMap is null");
        }
        target.setUserInfoMap(userInfoMap);

        return this;
    }

    public User build() {
        return target;
    }
}
