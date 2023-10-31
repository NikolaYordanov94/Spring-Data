package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table (name = "comments")
@Entity
public class Comment extends BaseEntity{

    @Column (name = "text_field", length = 100)
    private String textField;

    @ManyToOne
    private Diagnose diagnose;

    @ManyToOne
    private Visitation visitation;

}
