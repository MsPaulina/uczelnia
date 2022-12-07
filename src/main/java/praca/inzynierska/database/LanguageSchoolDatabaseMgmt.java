package praca.inzynierska.database;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import praca.inzynierska.model.Course;
import praca.inzynierska.model.Student;
import praca.inzynierska.repository.CourseRepository;
import praca.inzynierska.repository.StudentRepository;

import javax.persistence.criteria.CriteriaBuilder;

@Component
@AllArgsConstructor
public class LanguageSchoolDatabaseMgmt {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    //Methods

    public Course getCourseByID(Integer givenID) {
        return courseRepository.findById(givenID).orElseGet(() -> null);
    }

    public void addStudentToDB(Student givenStudent) {
        studentRepository.save(givenStudent);
    }

    public void removeStudentFromDB(Student givenStudent) {
        studentRepository.delete(givenStudent);
    }

    public Student getStudentByTheirID(Long givenID) {
        return studentRepository.findById(givenID).orElseGet(() -> null);
    }

    public Iterable<Student> getAllStudentsFromDB() {
        return studentRepository.findAll();
    }

    public void deleteStudentByID(Long givenID) {
        studentRepository.deleteById(givenID);
    }
}
