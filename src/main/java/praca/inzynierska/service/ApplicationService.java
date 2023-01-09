package praca.inzynierska.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import praca.inzynierska.model.Course;
import praca.inzynierska.model.CourseDTO;
import praca.inzynierska.model.Student;
import praca.inzynierska.model.StudentDTO;
import praca.inzynierska.repository.CourseRepository;
import praca.inzynierska.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;


    @Transactional
    public String createStudent(StudentDTO studentDTO) {
        Student student = new Student(
                studentDTO.getStudentIDinLanguageSchool(),
                studentDTO.getStudentName(),
                studentDTO.getStudentSurname()
        );

        Optional<Long> courseID = Optional.ofNullable(studentDTO.getCourseId());

        if (courseID.isPresent()) {
            Course course = findCourseByID(courseID.get());
            course.addStudent(student);
        }

        studentRepository.save(student);
        return "Student został zapisany w bazie danych i zostało mu przypisane ID: " + student.getStudentid();

    }

    @Transactional
    public String createCourse(CourseDTO courseDTO) {
        Course course = new Course(
                courseDTO.getName(),
                courseDTO.getDescription()
        );
        courseRepository.save(course);
        return "Kurs zapisany w bazie danych o podanym id: " + course.getId();
    }


    @Transactional
    public String updateStudentById(StudentDTO studentDTO, Long studentid) {

        Student student = findStudentByID(studentid);

        Course course = null;
        if (studentDTO.getCourseId() != null) {
            course = findCourseByID(studentDTO.getCourseId());
            course.addStudent(student);
        } else {
            student.getCourse().removeStudent(student);
        }
        student.setStudentName(studentDTO.getStudentName());
        student.setStudentSurname(studentDTO.getStudentSurname());

        studentRepository.save(student);

        return "studen zakualizowany/ o id: " + student.getStudentid();
    }


    private Course findCourseByID(Long courseID) {
        if (courseID == null) return null;

        Optional<Course> optionalCourse = courseRepository.findById(courseID);

        if (optionalCourse.isPresent()) {
            return optionalCourse.get();
        } else {
            throw new RuntimeException("kurs nie istnieje");
        }
    }

    private Student findStudentByID(Long studentID) {
        if (studentID == null) return null;

        Optional<Student> optionalStudent = studentRepository.findById(studentID);

        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new RuntimeException("student nie istnieje");
        }
    }

    @Transactional
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void removeStudentFromDB(Long givenStudent) {
        studentRepository.deleteById(givenStudent);
    }


    public Student getStudentByTheirID(Long givenID) {
        return studentRepository.findById(givenID).orElseGet(() -> null);
    }

}
