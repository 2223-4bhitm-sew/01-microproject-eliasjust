package at.htl.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Date validUntil;
    private double price;

    public Long getId() {
        return id;
    }

}
