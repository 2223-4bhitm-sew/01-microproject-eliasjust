package at.htl.control;

import at.htl.entity.Invoice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class InvoiceRepository {

    @Inject
    EntityManager em;

    public Invoice save(Invoice invoice) {
        return em.merge(invoice);
    }

    public List<Invoice> findAll() {
        return em.createNamedQuery("Invoice.findAll", Invoice.class).getResultList();
    }
    public Invoice findById(long id) {
        return em.find(Invoice.class, id);
    }

    public List<Invoice> findbyDate(String date) {
        return em.createNamedQuery("Invoice.findByDate", Invoice.class).setParameter("DATE", date).getResultList();
    }

    public void delete(long id) {
        Invoice invoice = em.find(Invoice.class, id);
        em.remove(invoice);
    }
}
