package at.htl.control;

import at.htl.entity.Customer;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
class CustomerRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    AgroalDataSource dataSource;

    @Test
    @Transactional
    void createCustomer() {
        var customer = new Customer("test", "user", "test.user@testen.com");
        var customer2 = new Customer("test1", "user1", "test.user@testen.com");

        customerRepository.persist(customer);
        customerRepository.persist(customer2);


        var table = new Table(dataSource, "CUSTOMER");
        assertThat(table)
                .row(0)
                .column("nname")
                .value().isEqualTo("user")
                .value().isEqualTo("user1");

    }
}