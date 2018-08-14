package finley.training.model.knowledge;

import finley.training.model.Entity;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Course extends Entity {

    private String courseId;

    private String subjectId;

    public Course() {
        super();
    }

    public Course(String subjectId) {
        this();
        this.subjectId = subjectId;
    }
}