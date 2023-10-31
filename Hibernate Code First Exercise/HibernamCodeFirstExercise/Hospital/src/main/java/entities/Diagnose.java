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
@Table (name = "diagnoses")
public class Diagnose extends BaseEntity{

    @Column (length = 60, nullable = false)
    private String name;

    @OneToMany (mappedBy = "diagnose")
    private List<Comment> comments;

    @ManyToMany
    private List<Patient> patients;
}
