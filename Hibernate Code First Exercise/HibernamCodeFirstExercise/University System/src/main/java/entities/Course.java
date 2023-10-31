package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "courses")
public class Course {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (length = 50)
    private String name;

    @Column (length = 100)
    private String description;

    @Column (name = "start_date")
    private Date startDate;

    @Column (name = "end_date")
    private Date endDate;

    @Column
    private Double credits;

    @ManyToMany
    private List<Student> students;

    @ManyToOne
    private Teacher teacher;

}
