package praca.inzynierska.myapi;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import praca.inzynierska.model.Course;
import praca.inzynierska.model.CourseDTO;
import praca.inzynierska.model.Student;
import praca.inzynierska.model.StudentDTO;
import praca.inzynierska.service.ApplicationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/myapi")
public class RestControllerStudent {

    @Autowired
    ApplicationService applicationService;

    @DeleteMapping("/deleteStudentFromDB/{id}")
    public ResponseEntity deleteStudentFromDB(@PathVariable Long id){
        applicationService.removeStudentFromDB(id);
        return ResponseEntity.ok("Student of id " + id + " has been removed.");
    }


    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(@PathVariable Long id) {
        Student retrievedStudent = applicationService.getStudentByTheirID(id);
        return retrievedStudent;
    }

    @GetMapping("/findAllStudents")
    public ResponseEntity<Iterable<Student>> findAllStudents() {
        System.out.println("Method /findAllStudentsInDatabase was called!");
        List response = applicationService.getAllStudents();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateStudentByID/{id}")
    public ResponseEntity updateStudentByID(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
        String response = applicationService.updateStudentById(studentDTO, id);
        return ResponseEntity.ok(response);
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