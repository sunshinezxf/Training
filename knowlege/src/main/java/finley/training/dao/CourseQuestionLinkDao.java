package finley.training.dao;

        import finley.training.model.knowledge.CourseQuestionLink;
        import util.ResultData;

        import java.util.Map;

public interface CourseQuestionLinkDao {

    ResultData query(Map<String, Object> condition);

    ResultData insert(CourseQuestionLink coursequestionlink);

    ResultData update(CourseQuestionLink coursequestionLink);
}
