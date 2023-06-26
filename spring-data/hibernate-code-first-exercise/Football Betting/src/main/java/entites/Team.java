package entites;

import entites.enums.TeamShortName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table
public class Team extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private String logo;

    @Column
    @Enumerated(EnumType.STRING)
    private TeamShortName shortName;

    @ManyToOne
    @JoinColumn
    private Color primaryColor;

    @ManyToOne
    @JoinColumn
    private Color secondaryColor;

    @ManyToOne
    private Town homeTown;

    @Column
    private BigDecimal budget;


}
