package praca.inzynierska.repository;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import praca.inzynierska.model.Course;

@Repository
public interface CourseRepository  extends CrudRepository<Course, Integer> {
}
