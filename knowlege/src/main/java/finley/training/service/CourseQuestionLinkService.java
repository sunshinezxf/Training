package finley.training.service;

import finley.training.model.knowledge.CourseQuestionLink;
import util.ResultData;

import java.util.Map;

public interface CourseQuestionLinkService {

    ResultData fetch(Map<String, Object> condition);

    ResultData create(CourseQuestionLink courseQuestionLink);
}
