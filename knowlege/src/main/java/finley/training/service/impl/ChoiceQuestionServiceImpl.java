package finley.training.service.impl;

import finley.training.dao.ChoiceQuestionDao;
import finley.training.model.knowledge.ChoiceQuestion;
import finley.training.service.ChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.ResponseCode;
import util.ResultData;

import java.util.Map;

@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {
    @Autowired
    private ChoiceQuestionDao choiceQuestionDao;

    public ResultData fetch(Map<String,Object> condition){
        ResultData result=new ResultData();
        ResultData response=choiceQuestionDao.query(condition);
        if(response.getResponseCode()== ResponseCode.RESPONSE_OK){
            result.setResponseCode(ResponseCode.RESPONSE_OK);
            result.setData(response.getData());
        }
        if(response.getResponseCode()==ResponseCode.RESPONSE_NULL){
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
            result.setDescription("No choice_question from database");
        }
        if(response.getResponseCode()==ResponseCode.RESPONSE_ERROR){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to fetch choice_question from database");
        }
        return result;
    }

    @Override

    public ResultData create(ChoiceQuestion choiceQuestion){
        ResultData result=new ResultData();
        ResultData response=choiceQuestionDao.insert(choiceQuestion);
        if(response.getResponseCode()!=ResponseCode.RESPONSE_OK){
            result.setResponseCode(ResponseCode.RESPONSE_OK);
            result.setData(response.getData());
        }else {
            response.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to insert choice_question from database");
        }
        return result;
    }

    @Override
    public ResultData revise(ChoiceQuestion choiceQuestion) {
        ResultData result=new ResultData();
        ResultData response=choiceQuestionDao.update(choiceQuestion);
        if(response.getResponseCode()==ResponseCode.RESPONSE_OK){
            result.setResponseCode(ResponseCode.RESPONSE_OK);
            result.setData(response.getData());
        }else{
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to update choice_question from database");
        }
        return result;
    }

}
