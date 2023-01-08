package at.htl.boundary;

import at.htl.control.TicketRepository;
import at.htl.entity.Ticket;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/ticket")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TicketResource {
    @Inject
    TicketRepository ticketRepository;
    @Inject
    Logger logger;

    @GET
    public List<Ticket> findAll(){
        return ticketRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Ticket findById(@PathParam("id") Long id){
        return ticketRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Ticket ticket) {
        ticketRepository.persist(ticket);
        logger.info("ticket created: " + ticket.getId());
        return Response.created(URI.create("/ticket/"+ticket.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Ticket ticket) {
        var entity = ticketRepository.findById(id);
        if (entity == null){
            logger.info("ticket not found: " + id+" will be created");
            return create(ticket);
        }else {
            entity.setPrice(ticket.getPrice());
            entity.setDateOfExpiry(ticket.getDateOfExpiry());
            entity.setInvoice(ticket.getInvoice());
            logger.info("ticket updated: "+entity.getId());
            return Response.ok().build();
        }

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        var entity = ticketRepository.findById(id);
        if(entity==null){
            logger.error("no ticket",new NotFoundException());
            throw new NotFoundException();
        }
        ticketRepository.delete(entity);
        logger.info("ticket deleted: " + entity.getId());
        return Response.ok().build();
    }

}
