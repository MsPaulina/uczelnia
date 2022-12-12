package praca.inzynierska.model;
import javax.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;


@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonProperty("id")
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade=CascadeType.PERSIST)
    @Getter
    private final Set<Student> students = new HashSet<>();

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addStudent(Student student){
        students.add(student);
    }
}
