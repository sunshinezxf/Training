package finley.training.dao;

import finley.training.model.knowledge.Course;
import util.ResultData;

import java.util.Map;

public interface CourseDao {
    ResultData query(Map<String, Object> condition);

    ResultData insert(Course course);
}
