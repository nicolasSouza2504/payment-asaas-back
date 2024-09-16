package org.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.dto.ServerSession;
import org.model.Payment;

import java.util.List;

@ApplicationScoped
public class PaymentRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public List<Payment> findByIdAndStatus(String paymentId, String status) {

        return entityManager.createQuery("SELECT p " +
                        " FROM Payment p " +
                        " WHERE UPPER(p.status) = UPPER(:status) " +
                        " AND p.paymentId = :paymentId " +
                        " AND p.userId = :userId " +
                        " ORDER BY p.paymentId ", Payment.class)
                .setParameter("paymentId", paymentId)
                .setParameter("status", status)
                .setParameter("userId", ServerSession.getSession().getUserLogin().getId())
                .getResultList();

    }


    @Transactional
    public List<Payment> findAll() {

        return entityManager.createQuery("SELECT p " +
                        " FROM Payment p " +
                        " WHERE p.userId = :userId  " +
                        " ORDER BY p.paymentId ", Payment.class)
                .setParameter("userId", ServerSession.getSession().getUserLogin().getId())
                .getResultList();

    }

}
