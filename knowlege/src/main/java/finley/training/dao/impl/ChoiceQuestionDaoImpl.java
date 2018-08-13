package finley.training.dao.impl;

import finley.training.dao.BaseDao;
import finley.training.dao.ChoiceQuestionDao;
import finley.training.model.knowledge.ChoiceQuestion;
import org.springframework.stereotype.Repository;
import util.ResultData;

import java.util.Map;

@Repository
public class ChoiceQuestionDaoImpl extends BaseDao implements ChoiceQuestionDao {
    public ResultData query(Map<String, Object> condition) {
        ResultData result = new ResultData();
        sqlSession.selectList("", condition);
        return result;
    }

    public ResultData insert(ChoiceQuestion choiceQuestion) {
        sqlSession.insert("", choiceQuestion);
        return null;
    }
}
