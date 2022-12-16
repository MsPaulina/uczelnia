package praca.inzynierska.myapi;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import praca.inzynierska.database.LanguageSchoolDatabaseMgmt;
import praca.inzynierska.model.Course;
import praca.inzynierska.model.CourseDTO;
import praca.inzynierska.model.Student;
import praca.inzynierska.model.StudentDTO;
import praca.inzynierska.service.ApplicationService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/myapi")
public class RestControllerStudent {

    @Autowired
    ApplicationService applicationService;

//    private LanguageSchoolDatabaseMgmt languageSchoolDatabaseMgmt;

//    @DeleteMapping("/deleteStudentFromDB/{id}")
//    public ResponseEntity deleteStudentFromDB(@PathVariable Long id){
////        languageSchoolDatabaseMgmt.removeStudentFromDB(id);
//        return ResponseEntity.ok("Student of id " + id + " has been removed.");
//    }

//    @GetMapping("/getStudentById/{id}")
//    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
//        return ResponseEntity.ok(languageSchoolDatabaseMgmt.getStudentByTheirID(id));
//    }

//    @GetMapping("/findAllStudentsInDatabase")
//    public ResponseEntity<Iterable<Student>> findAllBooksInLibrary() {
//        System.out.println("Method /findAllStudentsInDatabase was called!");
//        return ResponseEntity.ok(languageSchoolDatabaseMgmt.getAllStudentsFromDB());
//    }

    @PutMapping("/updateStudentByID/{id}")
    public ResponseEntity updateStudentByID(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {

        String response = applicationService.updateStudentById(studentDTO, id);
        return ResponseEntity.ok(response);

//
//        Optional<Student> studentOptional = Optional.ofNullable(languageSchoolDatabaseMgmt.getStudentByTheirID(id));
//        if (studentOptional.isPresent()) {
//            Student updatedStudent = studentOptional.get();
//            updatedStudent.setCourse(student.getCourse());
//            updatedStudent.setStudentName(student.getStudentName());
//            updatedStudent.setStudentSurname(student.getStudentSurname());
//            languageSchoolDatabaseMgmt.addStudentToDB(updatedStudent);
//        } else {
//            languageSchoolDatabaseMgmt.addStudentToDB(student);
//        }
//
//        return ResponseEntity.ok("Student has been successfully updated in the DB");
    }





//    private Optional<Course> doesCourseExistInTheDatabase(Integer courseID) {
//        return Optional.ofNullable(languageSchoolDatabaseMgmt.getCourseByID(courseID));
//    }

    private Course createNewCourse(String name, String description) {
        return new Course(name,description);
    }

    @PostMapping(path = "/addNewStudent")
    public ResponseEntity addNewStudent(@RequestBody StudentDTO studentDTO) {

        String response = applicationService.createStudent(studentDTO);


        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/addNewCourse")
    public ResponseEntity addNewCourse(@RequestBody CourseDTO courseDTO) {

        String response = applicationService.createCourse(courseDTO);


        return ResponseEntity.ok(response);
    }


}