package org.acme;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.jboss.logging.Logger;
import io.micrometer.core.annotation.Counted;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@Path("/api")
@RolesAllowed("**")
@SecuritySchemes(value = {
        @SecurityScheme(
                securitySchemeName = "http",
                type = SecuritySchemeType.HTTP,
                scheme = "Bearer")
        }
)
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
    @SecurityRequirement(name = "http")
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
    @SecurityRequirement(name = "http")
    public Response admin() {
        LOG.info("/admin accessed by user " + idToken.getName());
        return Response.ok().build();
    }
}
