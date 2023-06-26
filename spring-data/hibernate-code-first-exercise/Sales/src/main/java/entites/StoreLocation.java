package entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class StoreLocation extends BaseEntity {

    @Column
    private String locationName;

    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;

}
