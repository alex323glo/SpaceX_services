package com.alex323glo.spacex.model.user;

/**
 * User info class for user's phone number.
 * Has preset UserInfoType == UserInfoType.PHONE
 *
 * @author alex323glo
 * @version 1.0.0
 * @see UserInfo
 * @see UserInfoType
 */
public class Phone extends UserInfo {

    private String phoneNumber;

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.userInfoType = UserInfoType.PHONE;
    }

    /**
     * Getter for user info value.
     *
     * @return String user info.
     */
    @Override
    public String getInfo() {
        return phoneNumber;
    }

    /**
     * Setter for user info value.
     *
     * @param info initial value.
     */
    @Override
    public void setInfo(String info) {
        phoneNumber = info;
    }
}