package finley.training.model.knowledge;

import finley.training.model.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseQuestionLink extends Entity {

    private String courseId;

    private String questionId;

    private String CourseQuestionLinkId;

    public CourseQuestionLink() {
        super();
    }

    public CourseQuestionLink(String courseId, String questionId) {
        this();
        this.courseId = courseId;
        this.questionId = questionId;
    }
}