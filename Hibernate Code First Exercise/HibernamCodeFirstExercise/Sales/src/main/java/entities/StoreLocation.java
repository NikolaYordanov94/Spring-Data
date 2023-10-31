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
@Table (name = "stores_locations")
public class StoreLocation extends BaseEntity{

    @Column
    private String locationName;

    @OneToMany (mappedBy = "storeLocation")
    private Set<Sale> sales;
}
