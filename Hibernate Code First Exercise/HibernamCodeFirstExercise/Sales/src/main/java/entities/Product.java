package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Table (name = "products")
@Entity
public class Product extends BaseEntity{

    @Column (length = 50, unique = true, nullable = false)
    private String name;

    @Column
    private Double quantity;

    @Column
    private BigDecimal price;

    @OneToMany (mappedBy = "product")
    private Set<Sale> sales;

}
