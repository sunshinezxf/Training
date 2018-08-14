package finley.training.dao.impl;

import finley.training.dao.BaseDao;
import finley.training.dao.ChoiceQuestionDao;
import finley.training.model.knowledge.ChoiceQuestion;
import org.springframework.stereotype.Repository;
import util.IDGenerator;
import util.ResponseCode;
import util.ResultData;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
public class ChoiceQuestionDaoImpl extends BaseDao implements ChoiceQuestionDao {
    @Override
    public ResultData query(Map<String, Object> condition) {
        ResultData result = new ResultData();
        try{
            List<ChoiceQuestion> list=sqlSession.selectList("finley.training.choiceQuestion.query",condition);
            if(list.isEmpty()){
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData insert(ChoiceQuestion choiceQuestion) {
        ResultData result=new ResultData();
        choiceQuestion.setQuestionId(IDGenerator.generate("CHQ"));
        choiceQuestion.setCreateAt(new Timestamp(System.currentTimeMillis()));
        try{
            sqlSession.insert("finley.training.choice_question",choiceQuestion);
            result.setData(choiceQuestion);
        }catch(Exception e){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return null;
    }

    @Override
    public ResultData update(Map<String, Object> condition) {
        ResultData result=new ResultData();
        try{
            sqlSession.update("finley.training.choice_question.update",condition);
        }catch(Exception e){
            e.printStackTrace();
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }
}
