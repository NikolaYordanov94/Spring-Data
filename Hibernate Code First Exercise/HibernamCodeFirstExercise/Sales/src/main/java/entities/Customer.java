package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table (name = "customers")
public class Customer extends BaseEntity{

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String creditCardNumber;

    @OneToMany (mappedBy = "customer")
    Set<Sale> sales;
}
