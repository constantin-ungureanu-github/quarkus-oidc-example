package org.acme;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import io.micrometer.core.annotation.Counted;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@Path("/api")
@RolesAllowed("**")
public class JwtResource {

    private static final Logger LOG = Logger.getLogger(JwtResource.class);

    public JwtResource(JsonWebToken idToken) {
        this.idToken = idToken;
    }

    private final JsonWebToken idToken;

    @WithSpan
    @Counted
    @Path("/user")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "user", "admin" })
    public Response user() {
        LOG.info("/user accessed by user " + idToken.getName());
        return Response.ok().build();
    }

    @WithSpan
    @Counted
    @Path("/admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response admin() {
        LOG.info("/admin accessed by user " + idToken.getName());
        return Response.ok().build();
    }
}
