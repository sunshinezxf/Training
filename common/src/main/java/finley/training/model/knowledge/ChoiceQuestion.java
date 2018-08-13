package finley.training.model.knowledge;

import finley.training.model.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChoiceQuestion extends Entity {

    private String questionId;

    private String questionTitle;

    private String questionOption;

    public ChoiceQuestion() {
        super();
        this.questionOption = "";
    }

    public ChoiceQuestion(String questionTitle) {
        this();
        this.questionTitle = questionTitle;
    }

    public ChoiceQuestion(String questionTitle, String questionOption) {
        this(questionTitle);
        this.questionOption = questionOption;
    }

}