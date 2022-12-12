package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.entity.Customer;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("/customer")
public class CustomerResource {
    @Inject
    CustomerRepository customerRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findAll(){
        return customerRepository.findAll().list();

    }


    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Customer customer){
        customerRepository.persist(customer);
        return Response.ok().build();
    }

}
