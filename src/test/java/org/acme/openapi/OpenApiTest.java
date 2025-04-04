package org.acme.openapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class OpenApiTest {

    @Test
    void testOpenApi() {
        given()
            .when()
                .get("/openapi")
            .then()
                .statusCode(200)
                .body(containsString("openapi"));
    }
}
