package finley.training.model.knowledge;

import finley.training.model.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoursequestionLink extends Entity {

    private String courseId;

    private String questionId;


}