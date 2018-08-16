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
        try{
            sqlSession.insert("finley.training.choiceQuestion.insert",choiceQuestion);
            result.setData(choiceQuestion);
        }catch(Exception e){
            e.printStackTrace();
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData update(ChoiceQuestion choiceQuestion) {
        ResultData result=new ResultData();
        try{
            sqlSession.update("finley.training.choiceQuestion.update",choiceQuestion);
            result.setData(choiceQuestion);
        }catch(Exception e){
            e.printStackTrace();
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }
}
