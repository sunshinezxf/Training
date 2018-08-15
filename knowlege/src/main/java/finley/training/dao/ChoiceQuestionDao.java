package finley.training.dao;

import finley.training.model.knowledge.ChoiceQuestion;
import util.ResultData;

import java.util.Map;

public interface ChoiceQuestionDao {

    ResultData query(Map<String, Object> condition);

    ResultData insert(ChoiceQuestion choiceQuestion);

    ResultData update(ChoiceQuestion choiceQuestion);
}
