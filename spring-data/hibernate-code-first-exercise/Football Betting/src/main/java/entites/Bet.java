package entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class Bet extends BaseEntity {
    @Column
    private BigDecimal money;

    @Column
    private Date timeOfBet;

    @ManyToOne
    private User user;
}
