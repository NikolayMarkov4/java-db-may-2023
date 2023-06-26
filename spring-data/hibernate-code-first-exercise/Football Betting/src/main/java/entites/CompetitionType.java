package entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class CompetitionType extends BaseEntity{

    @Column(nullable = false)
    private String name;
}

