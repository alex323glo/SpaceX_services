package com.alex323glo.spacex.model.user;

/**
 * Model of Access Token for user in this application.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class AccessToken {

    private String token;
    private String userEmail;

    public AccessToken(String token, String userEmail) {
        this.token = token;
        this.userEmail = userEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "token='" + token + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessToken that = (AccessToken) o;

        if (!token.equals(that.token)) return false;
        return userEmail != null ? userEmail.equals(that.userEmail) : that.userEmail == null;
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }
}
