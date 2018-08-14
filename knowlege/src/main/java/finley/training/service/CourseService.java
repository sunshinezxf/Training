package finley.training.service;

import finley.training.model.knowledge.Course;
import util.ResultData;

import java.util.Map;

public interface CourseService {

    ResultData fetch(Map<String, Object> condition);

    ResultData create(Course course);
}

