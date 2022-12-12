package at.htl.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "MY_INVOICE")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_ID")
    private Long id;
    @Column(name = "I_DATE")
    private Date date;
    @Column(name = "I_DISCOUNT")
    private double discount;



    public Invoice(Date date, double discount) {
        this.date = date;
        this.discount = discount;
    }


    public Invoice() {

    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
