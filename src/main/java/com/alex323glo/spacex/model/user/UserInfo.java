package com.alex323glo.spacex.model.user;

/**
 * General interface for part of User Information.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see User
 * @see UserInfoType
 * */
public abstract class UserInfo {

    protected UserInfoType userInfoType;

    /**
     * Getter for user info value.
     *
     * @return String user info.
     * */
    public abstract String getInfo();

    /**
     * Setter for user info value.
     *
     * @param info initial value.
     * */
    public abstract void setInfo(String info);

    /**
     * Getter for info type.
     *
     * @return type of current user info part.
     * @see UserInfoType
     * */
    public UserInfoType getType() {
        return userInfoType;
    }

    /**
     * Setter for info type.
     *
     * @param userInfoType initial value.
     * @see UserInfoType
     * */
    public void setType(UserInfoType userInfoType) {
        if (userInfoType == null) {
            throw new NullPointerException("infoType is null");
        }
        this.userInfoType = userInfoType;
    }

}
