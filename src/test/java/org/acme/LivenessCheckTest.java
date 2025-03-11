package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import java.net.URL;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class LivenessCheckTest {

    private static final Logger LOG = Logger.getLogger(LivenessCheckTest.class);

    private static final String ALIVE = """
            {
                "status": "UP",
                "checks": [
                    {
                        "name": "alive",
                        "status": "UP"
                    }
                ]
            }""";

    @TestHTTPResource(value="/health/live", management=true)
    URL url;

    @Test
    void testLivenessProbe() {

        LOG.info(url);

        given()
            .when()
                .get(url)
            .then()
                .statusCode(200)
                .body(is(ALIVE));
    }
}
