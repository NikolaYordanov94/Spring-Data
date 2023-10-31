package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "credit_cards")
@Getter
@Setter
@NoArgsConstructor
public class CreditCard extends BillingDetail{

    @Enumerated (EnumType.STRING)
    private CardType cardType;

    @Column (name = "expiration_month")
    private Integer expirationMonth;

    @Column (name = "expiration_year")
    private Integer expirationYear;

}
