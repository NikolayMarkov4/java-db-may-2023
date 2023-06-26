package entites;

import entites.enums.ResultPredictionValue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class ResultPrediction extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ResultPredictionValue resultPrediction;
}
