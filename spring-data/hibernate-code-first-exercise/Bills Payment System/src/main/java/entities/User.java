package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class User extends BaseEntity {

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 120)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = {
            CascadeType.MERGE, CascadeType.DETACH
    })
    private Set<BillingDetails> billingDetails;
}
