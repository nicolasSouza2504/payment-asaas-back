package org.webhooks;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.dto.ServerSession;
import org.dto.payment.PaymentDto;
import org.services.payment.PaymentService;

@Path("update-status-payment")
public class PaymentWebHook {

    @Inject
    PaymentService paymentService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateStatusPayment(String jsonPayment) {

        PaymentDto payment = new Gson().fromJson(jsonPayment, PaymentDto.class);

        paymentService.updateStatusPayment(payment);

        System.out.println(ServerSession.getSession().getUserName() + " - Payment status updated: " + payment.getEvent());

        return Response.ok().build();

    }

}
