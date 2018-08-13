package finley.training.dao;

import util.ResultData;

import java.util.Map;

public interface CourseDao {
    ResultData query(Map<String, Object> condition);
}
