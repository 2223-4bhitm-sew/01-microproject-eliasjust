package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.entity.Customer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CustomerResourceTest {



   @Inject
   CustomerResource customerResource;


    Customer customer1 = new Customer("Hans", "Müller", "hans.müller@mueller.com");
    Customer customer2 = new Customer("Peter", "Müller", "peter.müller@mueller.com");

    @Test
    @Transactional
    void findAll() {

     customerResource.customerRepository.persist(customer1);
     customerResource.customerRepository.persist(customer2);

        given()
                .when().get("/customer")
                .then()
                .statusCode(200)
                        .body("size()", equalTo(2));

    }

    @Test
    void findById() {



    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}