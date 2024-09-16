package org.services.payment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.dto.payment.PaymentDto;
import org.model.Payment;
import org.repositories.PaymentRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class PaymentService {

    @Inject
    EntityManager entityManager;

    @Inject
    PaymentRepository paymentRepository;

    @Transactional
    public void updateStatusPayment(PaymentDto paymentStatusDto) {

        if (validStatusUpdate(paymentStatusDto)) {

            List<Payment> payments = paymentRepository.findByIdAndStatus(paymentStatusDto.getPayment().getId(), paymentStatusDto.getEvent());

            if (payments == null || payments.isEmpty()) {
                createPayment(paymentStatusDto);
            } else {
                System.out.println(" Payment update already saved: " + paymentStatusDto.getEvent());
            }

        }

    }

    @Transactional
    public void createPayment(PaymentDto paymentStatusDto) {

        Payment payment = buildPayment(paymentStatusDto);

        entityManager.persist(payment);

    }

    private Payment buildPayment(PaymentDto paymentStatusDto) {

        Payment payment = new Payment();

        payment.setPaymentId(paymentStatusDto.getPayment().getId());
        payment.setStatus(paymentStatusDto.getEvent());
        payment.setValue(paymentStatusDto.getPayment().getValue());
        payment.setBillingType(paymentStatusDto.getPayment().getBillingType());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            payment.setDateCreated(dateFormat.parse(paymentStatusDto.getDateCreated()));
        } catch (Throwable t) {
            payment.setDateCreated(new Date());
        }

        return payment;

    }

    private Boolean validStatusUpdate(PaymentDto paymentStatusDto) {
        return  paymentStatusDto != null && StringUtils.isNotEmpty(paymentStatusDto.getEvent())
                && paymentStatusDto.getPayment() != null && paymentStatusDto.getPayment().getId() != null;
    }

}
