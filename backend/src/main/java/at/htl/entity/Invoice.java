package at.htl.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
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
