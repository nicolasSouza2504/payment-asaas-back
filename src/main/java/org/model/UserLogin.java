package org.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_login")
public class UserLogin {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "salt_password", columnDefinition = "TEXT")
    private String saltPassword;

    @Column(name = "user_name", columnDefinition = "TEXT", unique = true)
    private String userName;

    @Column(name = "api_key_payments", columnDefinition = "TEXT")
    private String apiKeyPayments;

    @Column(name = "api_key", columnDefinition = "TEXT")
    private String apiKey;

}
