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

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok(languageSchoolDatabaseMgmt.getStudentByTheirID(id));
    }

    @GetMapping("/findAllStudentsInDatabase")
    public ResponseEntity<Iterable<Student>> findAllBooksInLibrary() {
        System.out.println("Method /findAllStudentsInDatabase was called!");
        return ResponseEntity.ok(languageSchoolDatabaseMgmt.getAllStudentsFromDB());
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

        // if course id exist check if course exist in database and then if it is true
        // add student to property course if not save new student with new course
        if(courseID.isPresent()) {
            Optional<Course> optionalCourse = doesCourseExistInTheDatabase(courseID.get());
            if(optionalCourse.isPresent()){
                Course course = optionalCourse.get();

                // create relationshiop = Bi-directional
                course.addStudent(student);
                student.setCourse(course);
            }
            else{
                Course newCourse = createNewCourse(student.getCourse().getName(), student.getCourse().getDescription());
                student.setCourse(newCourse);
            }
        }
        //adding to the database
        languageSchoolDatabaseMgmt.addStudentToDB(student);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/myapi/getStudentById")
                .path("/{id}")
                .buildAndExpand(student.getStudentid())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
