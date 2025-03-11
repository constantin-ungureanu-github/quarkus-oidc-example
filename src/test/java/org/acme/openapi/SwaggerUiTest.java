package org.acme.openapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class SwaggerUiTest {

    @Test
    void testSwaggerUi() {
        given()
            .when()
                .get("/swagger-ui")
            .then()
                .statusCode(200)
                .body(containsString("/openapi"));
    }
}
