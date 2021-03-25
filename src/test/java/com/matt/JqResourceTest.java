package com.matt;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class JqResourceTest {

    @Test
    public void testJvmEndpoint() {
        given()
          .when().get("/jvm")
          .then()
             .statusCode(200);
    }

    @Test
    public void testJqEndpoint() {
        Response response = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("foo", Map.of("bar", "1")))
                .when()
                .post("/jq")
                .thenReturn();

        String responseBody = response.asString();
        int statusCode = response.statusCode();
        assertThat(statusCode).isEqualTo(200);
        assertThat(responseBody).isEqualTo("1");
    }
}