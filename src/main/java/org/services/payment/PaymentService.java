package org.services.payment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.dto.payment.PaymentDto;
import org.model.Payment;
import org.repositories.PaymentRepository;

import java.util.List;

@ApplicationScoped
public class PaymentService {

    @Inject
    PaymentRepository paymentRepository;

    @Transactional
    public void updateStatusPayment(PaymentDto paymentStatusDto) {

        if (validStatusUpdate(paymentStatusDto)) {

            PaymentDto.Payment payment = paymentStatusDto.getPayment();

            List<Payment> payments = paymentRepository.findByIdAndStatus(paymentStatusDto.getPayment().getId(), paymentStatusDto.getPayment().getStatus());

            if (payments != null && !payments.isEmpty()) {
                createPayment(paymentStatusDto);
            }

        }

    }

    @Transactional
    public void createPayment(PaymentDto paymentStatusDto) {

        Payment payment = new Payment();



    }

    private Boolean validStatusUpdate(PaymentDto paymentStatusDto) {
        return  paymentStatusDto != null && StringUtils.isNotEmpty(paymentStatusDto.getEvent())
                && paymentStatusDto.getPayment() != null && paymentStatusDto.getPayment().getId() != null;
    }

}
