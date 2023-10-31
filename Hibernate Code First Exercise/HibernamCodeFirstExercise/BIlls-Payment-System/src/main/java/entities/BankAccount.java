package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class BankAccount extends BillingDetail{

    @Column
    private String name;

    @Column
    private String swift;
}
