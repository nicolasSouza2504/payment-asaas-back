package org.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.model.Payment;
import org.repositories.PaymentRepository;

import java.util.List;

@Path("payments")
public class PaymentResource {

    @Inject
    PaymentRepository paymentRepository;

    @GET
    @Path("list-all-updates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllUpdates() {

        List<Payment> payments = paymentRepository.findAll();

        return Response.ok(payments).build();

    }

}
