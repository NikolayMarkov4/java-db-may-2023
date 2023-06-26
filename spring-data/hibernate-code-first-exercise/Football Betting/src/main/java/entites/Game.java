package entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class Game extends BaseEntity {

    @OneToOne
    @JoinColumn
    private Team homeTeam;

    @OneToOne
    @JoinColumn
    private Team awayTeam;

    @Column
    private Short homeGoals;

    @Column
    private Short awayGoals;

    @Column
    private Date date;

    @Column
    private Double homeTeamWinBetRate;

    @Column
    private Double awayTeamWinBetRate;

    @Column
    private Double drawGameBetRate;

    // one to one || many to one
    @ManyToOne
    @JoinColumn
    private Round round;

    @ManyToOne
    @JoinColumn
    private Competition competition;
}