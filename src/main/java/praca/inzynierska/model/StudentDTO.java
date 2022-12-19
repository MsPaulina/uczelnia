package praca.inzynierska.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDTO {

    private Long studentid;

    private String studentIDinLanguageSchool;

    private String studentName;

    private String studentSurname;

    private Long courseId;

}
