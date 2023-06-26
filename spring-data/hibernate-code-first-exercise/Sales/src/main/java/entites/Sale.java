package entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Sale extends BaseEntity {

    // bi-directional relation
    @ManyToOne
    private Product product;

    @OneToMany
    private List<Product> test;

    // bi-directional relation
    @ManyToOne
    private Customer customer;

    // bi-directional relation
    @ManyToOne
    private StoreLocation storeLocation;

    @Column
    private Date date;
}
