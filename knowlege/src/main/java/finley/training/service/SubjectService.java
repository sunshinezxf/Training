package finley.training.service;

import finley.training.model.knowledge.Subject;
import util.ResultData;

import java.util.Map;

public interface SubjectService {

    ResultData fetch(Map<String, Object> condition);

    ResultData create(Subject subject);
}
