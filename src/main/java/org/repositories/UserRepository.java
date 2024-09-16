package org.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.model.UserLogin;

@ApplicationScoped
public class UserRepository {


    @Inject
    EntityManager entityManager;

    @Transactional
    public UserLogin findByUserName(String userName) {

        return entityManager.createQuery("SELECT u FROM UserLogin u WHERE UPPER(u.userName) = :userName", UserLogin.class)
                .setParameter("userName", userName.toUpperCase())
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

    }

    @Transactional
    public UserLogin findByApiKey(String apiKey) {

        return entityManager.createQuery("SELECT u FROM UserLogin u WHERE u.apiKey = :apiKey", UserLogin.class)
                .setParameter("apiKey", apiKey)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

    }

}

