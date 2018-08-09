package finley.training.model.knowledge;

import finley.training.model.Entity;

public class Subject extends Entity{

    private String subjectId;

    private String subjectName;

    private String subjectLink;

    public Subject() {
        super();
        this.subjectLink = "";
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public Subject(String subjectName, String subjectLink) {
        this(subjectName);
        this.subjectLink = subjectLink;
    }
}
