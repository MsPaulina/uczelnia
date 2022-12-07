package praca.inzynierska.model;
import java.util.Objects;
import javax.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter
    @Getter
    private Integer studentid;

    @Setter
    @Getter
    private String studentname;


    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="course_id")
    @Getter
    @Setter
    private Course course;

    public Student(String name, Course course) {
        this.studentname  = name;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentname, student.studentname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentname);
    }
}
