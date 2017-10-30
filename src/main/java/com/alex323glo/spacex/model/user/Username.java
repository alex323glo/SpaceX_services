package com.alex323glo.spacex.model.user;

/**
 * User info class for user's username.
 * Has preset UserInfoType == UserInfoType.USERNAME
 *
 * @author alex323glo
 * @version 1.0.0
 * @see UserInfo
 * @see UserInfoType
 */
public class Username extends UserInfo {

    private String name;

    public Username(String name) {
        this.name = name;
        userInfoType = UserInfoType.USERNAME;
    }

    /**
     * Getter for user info value.
     *
     * @return String user info.
     */
    @Override
    public String getInfo() {
        return name;
    }

    /**
     * Setter for user info value.
     *
     * @param info initial value.
     */
    @Override
    public void setInfo(String info) {
        name = info;
    }
}
