package entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "billing_details")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public abstract class BillingDetail extends BaseEntity{

    @Column (nullable = false, unique = true)
    private String number;

    @ManyToOne
    private User owner;

}
