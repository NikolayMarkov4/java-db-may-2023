package entities;

import javax.persistence.*;
import java.util.List;

// POJO - plain old java object
@Entity
@Table(name = "wizards")
public class Wizard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60)
    private String lastName;

    @Column(length = 1000, columnDefinition = "TEXT")
    private String note;

    @Column(nullable = false)
    private Integer age;

    // one wizard (current pojo) to one magic wand
    @OneToOne
    @JoinColumn
    private MagicWand magicWand;

    // many wizards -> one magic wand
    //    @ManyToOne
    //    private MagicWand magicWand2;

    // one wizard -> many deposits
    @OneToMany
    @JoinTable(name = "wizard_deposits")
    private List<Deposit> deposits;

    //many wizard -> many deposits
    //    @ManyToMany
    //    private List<Deposit> deposits2;
}

