package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.entity.Customer;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;
import javax.ws.rs.core.Response;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CustomerResourceTest {




    @Test
    @Transactional
    void findAll() {

        given()
                .when().get("/customer")
                .then()
                .statusCode(200)
                        .body("size()", notNullValue());
    }

    @Transactional
    @Test
    void testfindById() {
     given()
             .when().get("/customer/2")
             .then()
             .statusCode(200)
             .body("vname",is("Hans"));

    }


    @Test
    public void createCustomer() {
        var customer = new Customer();
        customer.setEmail("max@mustermann.com");
        customer.setNname("Mustermann");
        customer.setVname("Max");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(customer)
                .when().post("/customer")
                .then().statusCode(201);
    }

    @Test
    public void updateCustomer() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"vname\": \"Max\",\"nname\": \"Muster\",\"email\": \"max@muster.com\"}")
                .when().put("/customer/2")
                .then().statusCode(200);
    }

}