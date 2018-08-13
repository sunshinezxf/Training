package finley.training.model.knowledge;

import finley.training.model.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Subject extends Entity{

    private String subjectId;

    private String subjectName;

    private String subjectLink;

    public Subject() {
        super();
        this.subjectLink = "";
    }

    public Subject(String subjectName) {
        this();
        this.subjectName = subjectName;
    }

    public Subject(String subjectName, String subjectLink) {
        this(subjectName);
        this.subjectLink = subjectLink;
    }
}
