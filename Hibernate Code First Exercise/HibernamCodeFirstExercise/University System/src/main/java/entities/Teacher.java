package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "teachers")
public class Teacher extends BaseEntity{

    @Column
    private String email;

    @Column (name = "salary_per_hour")
    private BigDecimal salaryPerHour;

    @OneToMany (mappedBy = "teacher")
    private List<Course> courses;
}
