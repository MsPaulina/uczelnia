package praca.inzynierska.repository;

import praca.inzynierska.model.Student;
import org.springframework.data.repository.CrudRepository;


public interface StudentRepository extends CrudRepository<Student, Long> {
}
