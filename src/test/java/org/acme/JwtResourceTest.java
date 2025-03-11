package org.acme;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.keycloak.client.KeycloakTestClient;

@QuarkusTest
class JwtResourceTest {

    KeycloakTestClient keycloakClient = new KeycloakTestClient();

    @Test
    void testAdminAccess() {
        // Admin can access admin API
        given()
            .auth()
                .oauth2(getAccessToken("admin"))
            .when()
                .get("/api/admin")
            .then()
                .statusCode(200);

        // Alice cannot access admin API
        given()
            .auth()
                .oauth2(getAccessToken("alice"))
            .when()
                .get("/api/admin")
            .then()
                .statusCode(403);
    }

    @Test
    void testUserAccess() {
        // Admin can access user API
        given()
            .auth()
                .oauth2(getAccessToken("admin"))
            .when()
                .get("/api/user")
            .then()
                .statusCode(200);

        // Alice can access user API
        given()
            .auth()
                .oauth2(getAccessToken("alice"))
            .when()
                .get("/api/user")
            .then()
                .statusCode(200);
    }

    protected String getAccessToken(String userName) {
        return keycloakClient.getAccessToken(userName);
    }
}
