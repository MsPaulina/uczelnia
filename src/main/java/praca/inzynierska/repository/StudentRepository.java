package praca.inzynierska.repository;
import org.springframework.stereotype.Repository;

import praca.inzynierska.model.Student;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
