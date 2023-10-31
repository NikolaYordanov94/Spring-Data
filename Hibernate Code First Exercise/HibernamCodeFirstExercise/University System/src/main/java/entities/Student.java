package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "students")
public class Student extends BaseEntity{

    @Column (name = "average_grade")
    private Double averageGrade;

    @Column
    private String attendance;

    @ManyToMany (mappedBy = "students")
    List<Course> courses;
}
