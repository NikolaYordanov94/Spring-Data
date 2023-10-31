package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "medicaments")
public class Medicament extends BaseEntity{

    @Column (length = 60, nullable = false)
    private String name;

    @ManyToMany
    private List<Patient> patients;
}
