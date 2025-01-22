package org.acme;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@Path("/api")
@RolesAllowed("**")
public class JwtResource {

    @Inject
    JsonWebToken accessToken;

    @WithSpan
    @Path("/user")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "user", "admin" })
    public Response user() {
        return Response.ok().build();
    }

    @WithSpan
    @Path("/admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response admin() {
        return Response.ok().build();
    }
}
