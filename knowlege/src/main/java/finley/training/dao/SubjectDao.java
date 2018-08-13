package finley.training.dao;

import finley.training.model.knowledge.Subject;
import util.ResultData;

import java.util.Map;

public interface SubjectDao {

    ResultData query(Map<String, Object> condition);

    ResultData insert(Subject subject);

    ResultData update(Subject subject);
}
