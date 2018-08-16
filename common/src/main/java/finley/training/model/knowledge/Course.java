package finley.training.model.knowledge;

import finley.training.model.Entity;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Course extends Entity {

    private String courseId;

    private String subjectId;

    private String courseDescription;

    public Course() {
        super();
    }

    public Course(String subjectId, String courseDescription) {
        this();
        this.subjectId = subjectId;
        this.courseDescription = courseDescription;
    }
}