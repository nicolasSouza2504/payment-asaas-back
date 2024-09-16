package org.dto;

import lombok.Data;
import org.model.UserLogin;

@Data
public class Session {

    private String authToken;
    private UserLogin userLogin;
    private String asaasAccessToken;
    private String userName;

    public Session(String authToken, String userName, UserLogin userLogin) {
        this.authToken = authToken;
        this.userName = userName;
        this.userLogin = userLogin;
    }

}
