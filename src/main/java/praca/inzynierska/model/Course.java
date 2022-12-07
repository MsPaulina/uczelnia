package praca.inzynierska.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
    private String name;

    @Getter
    @Setter
    private String description;

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
