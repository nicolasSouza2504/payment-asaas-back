package org.resources.payment;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dto.payment.PaymentExibitionDto;
import org.model.Payment;
import org.repositories.PaymentRepository;
import org.services.payment.PaymentService;

import java.util.List;

@Path("payments")
public class PaymentResource {

    @Inject
    PaymentRepository paymentRepository;

    @Inject
    PaymentService paymentService;

    @GET
    @Path("/list-all-updates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllUpdates() {

        List<Payment> payments = paymentRepository.findAll();

        List<PaymentExibitionDto> paymentsExibition = paymentService.convertToPaymentExibitionDto(payments);

        return Response.ok(new Gson().toJson(paymentsExibition)).build();

    }

}
