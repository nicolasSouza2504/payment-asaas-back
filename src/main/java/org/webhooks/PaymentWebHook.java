package org.webhooks;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.dto.ServerSession;

@Path("update-status-payment")
public class PaymentWebHook {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateStatusPayment(String jsonPayment) {

        String userName = ServerSession.getSession().getUserName();

        System.out.println(userName + " - Payment status updated: " + jsonPayment);

        return Response.ok().build();

    }

}
