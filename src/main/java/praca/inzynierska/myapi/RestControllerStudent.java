package praca.inzynierska.myapi;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import praca.inzynierska.database.LanguageSchoolDatabaseMgmt;
import praca.inzynierska.model.Course;
import praca.inzynierska.model.Student;

import java.net.URI;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/myapi")
public class RestControllerStudent {

    private LanguageSchoolDatabaseMgmt languageSchoolDatabaseMgmt;

    @DeleteMapping("/deleteStudentFromDB/{id}")
    public ResponseEntity deleteStudentFromDB(@PathVariable Long id){
        languageSchoolDatabaseMgmt.removeStudentFromDB(id);
        return ResponseEntity.ok("Student of id " + id + " has been removed.");
    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(languageSchoolDatabaseMgmt.getStudentByTheirID(id));
    }

    @GetMapping("/findAllStudentsInDatabase")
    public ResponseEntity<Iterable<Student>> findAllBooksInLibrary() {
        System.out.println("Method /findAllStudentsInDatabase was called!");
        return ResponseEntity.ok(languageSchoolDatabaseMgmt.getAllStudentsFromDB());
    }

    @PutMapping("/updateStudentByID/{id}")
    public ResponseEntity updateStudentByID(@RequestBody Student student, @PathVariable Long id) {

        Optional<Student> studentOptional = Optional.ofNullable(languageSchoolDatabaseMgmt.getStudentByTheirID(id));
        if (studentOptional.isPresent()) {
            Student updatedStudent = studentOptional.get();
            updatedStudent.setCourse(student.getCourse());
            updatedStudent.setStudentName(student.getStudentName());
            updatedStudent.setStudentSurname(student.getStudentSurname());
            languageSchoolDatabaseMgmt.addStudentToDB(updatedStudent);
        } else {
            languageSchoolDatabaseMgmt.addStudentToDB(student);
        }

        return ResponseEntity.ok("Student has been successfully updated in the DB");
    }


    private Optional<Course> doesCourseExistInTheDatabase(Integer courseID) {
        return Optional.ofNullable(languageSchoolDatabaseMgmt.getCourseByID(courseID));
    }

    private Course createNewCourse(String name, String description) {
        return new Course(name,description);
    }

    @PostMapping(path = "/addNewStudent")
    public ResponseEntity addNewStudent(@RequestBody Student student) {

        Optional<Integer> courseID = Optional.ofNullable(student.getCourse().getId());

        if(courseID.isPresent()) {
            Optional<Course> optionalCourse = doesCourseExistInTheDatabase(courseID.get());
            if(optionalCourse.isPresent()){
                Course course = optionalCourse.get();

                course.addStudent(student);
                student.setCourse(course);
            }
            else{
                Course newCourse = createNewCourse(student.getCourse().getName(), student.getCourse().getDescription());
                student.setCourse(newCourse);
            }
        }
        languageSchoolDatabaseMgmt.addStudentToDB(student);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/myapi/getStudentById")
                .path("/{id}")
                .cloneBuilder()
                .buildAndExpand(student.getStudentid())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
