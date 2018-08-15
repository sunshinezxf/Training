package finley.training.service;

import finley.training.model.knowledge.ChoiceQuestion;
import util.ResultData;

import java.util.Map;

public interface ChoiceQuestionService {
    ResultData fetch(Map<String,Object> condition);

    ResultData create(ChoiceQuestion choiceQuestion);

    ResultData revise(ChoiceQuestion choiceQuestion);
}