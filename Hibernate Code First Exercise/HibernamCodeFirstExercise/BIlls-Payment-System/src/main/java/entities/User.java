package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "users")
public class User extends BaseEntity{

    @Column (name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column (name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column (nullable = false, length = 60)
    private String email;

    @Column (nullable = false, length = 30)
    private String password;


}
