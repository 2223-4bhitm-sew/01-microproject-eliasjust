package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.entity.Customer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    @Inject
    CustomerRepository customerRepository;


    @GET
    public List<Customer> findAll(){
        return customerRepository.findAll().list();

    }

    @GET
    @Path("/{id}")
    public Customer findById(@PathParam("id") Long id){
        return customerRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Customer customer){
        customerRepository.persist(customer);
        return Response.created(URI.create("/customer/"+customer.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Customer update(@PathParam("id") Long id, Customer customer){
        var entity = customerRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.setNname(customer.getNname());
        entity.setVname(customer.getVname());
        entity.setEmail(customer.getEmail());
        return  entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id){
        var entity = customerRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        customerRepository.delete(entity);
    }

}
