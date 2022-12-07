package praca.inzynierska.model;
import java.util.Objects;
import javax.persistence.*;

import com.sun.istack.NotNull;
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
    private Long studentid;

    @Setter
    @Getter
    private String studentIDinLanguageSchool;

    @Setter
    @Getter
    private String studentName;

    @Setter
    @Getter
    private String studentSurname;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="course_id")
    @Getter
    @Setter
    private Course course;

    public Student(String name, Course course, String studentName, String studentSurname) {
        this.studentIDinLanguageSchool = name;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentIDinLanguageSchool, student.studentIDinLanguageSchool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentIDinLanguageSchool);
    }
}
