package at.htl.entity;

import net.bytebuddy.dynamic.scaffold.MethodGraph;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Customer {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Invoice> invoices;

    private String vorname;
    private String nname;
    private String email;




    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public Customer() {
    }
}
