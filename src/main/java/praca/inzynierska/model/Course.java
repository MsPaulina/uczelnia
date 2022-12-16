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
    private Long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @Getter
    private final Set<Student> students = new HashSet<>();

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addStudent(Student student){
        students.add(student);
        student.setCourse(this);
    }

    public void removeStudent(Student student){
        students.remove(student);
        student.setCourse(null);
    }
}