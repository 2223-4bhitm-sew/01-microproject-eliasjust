package at.htl.boundary;

import at.htl.control.InvoiceRepository;
import at.htl.entity.Invoice;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Path("/invoice")
public class InvoiceResource {
    @Inject
    Logger logger;

    @Inject
    InvoiceRepository invoiceRepository;

    private List<Invoice> invoices = new LinkedList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Invoice findById(@PathParam("id") long id) {
        logger.info(id);
        return invoiceRepository.findById(id);
    }

    @GET
    @Path("date")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invoice> findById(
            @QueryParam("date") String date
    ) {
        logger.info(date);
        return invoiceRepository.findbyDate(date);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Invoice invoice, @Context UriInfo uriInfo) throws Exception {
        Invoice savedInvoice = invoiceRepository.save(invoice);
        logger.info("Invoice created: " + savedInvoice.getId());
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(savedInvoice.getId().toString())
                .build();
        return Response.created(location).build();
    }


    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Invoice invoice){
        return Response.noContent().build();
    }


}
