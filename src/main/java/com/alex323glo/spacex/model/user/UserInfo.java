package com.alex323glo.spacex.model.user;

/**
 * Part of User Information.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see User
 * @see InfoType
 * */
public class UserInfo {

    private InfoType infoType;
    private String value;

    public UserInfo() {
    }

    public UserInfo(InfoType infoType, String value) {
        this.infoType = infoType;
        this.value = value;
    }

    /**
     * Getter for user info value.
     *
     * @return String user info.
     * */
    public String getInfo() {
        return value;
    }

    /**
     * Setter for user info value.
     *
     * @param value initial value.
     * */
    public void setInfo(String value) {
        this.value = value;
    }

    /**
     * Getter for info type.
     *
     * @return type of current user info part.
     * @see InfoType
     * */
    public InfoType getType() {
        return infoType;
    }

    /**
     * Setter for info type.
     *
     * @param infoType initial value.
     * @see InfoType
     * */
    public void setType(InfoType infoType) {
        if (infoType == null) {
            throw new NullPointerException("infoType is null");
        }
        this.infoType = infoType;
    }

}
