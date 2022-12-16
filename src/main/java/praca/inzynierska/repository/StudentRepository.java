package praca.inzynierska.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import praca.inzynierska.model.Student;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
