package praca.inzynierska.repository;

import org.springframework.data.repository.CrudRepository;
import praca.inzynierska.model.Course;

public interface CourseRepository  extends CrudRepository<Course, Integer> {
}
