package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "patients")
public class Patient extends BaseEntity{

    @Column (name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column (name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Column (length = 60, nullable = false)
    private String address;

    @Column (length = 60, nullable = false, unique = true)
    private String email;

    @Column (name = "date_of_birth")
    private Date dateOfBirth;

    @Column (columnDefinition = "Blob")
    private String picture;

    @Column (name = "has_medical_insurance")
    private boolean hasMedicalInsurance;


    @OneToMany (mappedBy = "patient")
    private List<Visitation> visitations;

    @ManyToMany (mappedBy = "patients")
    private List<Diagnose> diagnoses;

    @ManyToMany (mappedBy = "patients")
    private List<Medicament> medicaments;


}
