package org.services.requesthandler;

import com.google.gson.Gson;
import io.vertx.core.json.JsonObject;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.apache.commons.lang3.StringUtils;
import org.dto.ServerSession;
import org.dto.Session;
import org.services.login.LoginService;
import org.services.redis.RedisService;
import org.utils.UtilErrorRest;

import java.util.Map;

@Provider
@Dependent
@Priority(Priorities.AUTHORIZATION)
public class RequestHandler implements ContainerRequestFilter {

    @Inject
    RedisService redisService;

    @Inject
    LoginService loginService;

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String path = requestContext.getUriInfo().getPath();

        if (!publicPath(path)) {

            Session session = verifySession(requestContext);

            if (session == null) {
                UtilErrorRest.throwResponseError("Unauthorized", Response.Status.UNAUTHORIZED.getStatusCode());
            } else {
                ServerSession.setSession(session);
            }

        }

    }

    public Session verifySession(ContainerRequestContext requestContext) {

        Session session = null;

        String authToken = getAuthToken(requestContext);
        String asaasAccessToken = requestContext.getHeaderString("asaas-access-token");

        if (StringUtils.isNotEmpty(authToken)) {

            JsonObject jsonSession = StringUtils.isNotEmpty(authToken) ? (JsonObject) redisService.get(authToken) : null;

            if (jsonSession != null) {
                session = new Gson().fromJson(jsonSession.toString(), Session.class);
            }

        } else if (StringUtils.isNotEmpty(asaasAccessToken)) {
            session = loginService.loginByApiKey(asaasAccessToken);
        }

        return session;

    }

    private String getAuthToken(ContainerRequestContext requestContext) {

        Map<String, Cookie> cookies = requestContext.getCookies();

        if (cookies.containsKey("authToken")) {

            Cookie authToken = cookies.get("authToken");

            if (authToken != null) {
                return authToken.getValue();
            }

        }

        return null;

    }

    public Boolean publicPath(String path) {
        return path != null && path.equals("/login") || path.equals("/register/user") || path.equals("/ping");
    }

}
