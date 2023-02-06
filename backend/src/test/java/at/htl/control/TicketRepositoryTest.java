package at.htl.control;

import at.htl.entity.Customer;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class TicketRepositoryTest {
    @Inject
    TicketRepository repository;



}