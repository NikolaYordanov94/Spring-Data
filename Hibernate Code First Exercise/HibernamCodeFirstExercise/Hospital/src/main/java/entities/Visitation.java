package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table (name = "visitations")
@Entity
public class Visitation extends BaseEntity{

    @Column (nullable = false)
    private Date date;

    @OneToMany (mappedBy = "visitation")
    private List<Comment> comments;

    @ManyToOne
    private Patient patient;
}
