package model;

import java.util.Map;

/**
 * Created by alex323glo on 27.10.17.
 */
public class User {
    private String email;
    private String password;
    private Map<UserInfoType, UserInfo> userInfoMap;
}
