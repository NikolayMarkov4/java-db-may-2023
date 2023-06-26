package entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class CreditCard extends BillingDetails {

    @Column(name = "card_type", nullable = false, length = 30)
    private String cardType;

    @Column(name = "exp_month", nullable = false)
    private Short expirationMonth;

    @Column(name = "exp_year", nullable = false)
    private Short expirationYear;
}
