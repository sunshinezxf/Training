package finley.training.service;

import util.ResultData;

import java.util.Map;

public interface SubjectService {

    ResultData fetch(Map<String, Object> condition);
}
