package finley.training.controller;

import finley.training.model.knowledge.ChoiceQuestion;
import finley.training.service.ChoiceQuestionService;
import form.knowledge.ChoiceQuestionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.ResponseCode;
import util.ResultData;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/knowledge/choiceQuestion")
public class ChoiceQuestionController {
    @Autowired
    private ChoiceQuestionService choiceQuestionService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResultData getChoice() {
        ResultData result = new ResultData();
        Map<String, Object> condition = new HashMap<>();
        condition.put("blockFlag", false);
        ResultData response = choiceQuestionService.fetch(condition);
        if (response.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
            result.setDescription("No choice question found from database");
        }
        if (response.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to retrieve choice question information from database");
        }
        if (response.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_OK);
            result.setData(response.getData());
        }
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/create")
    public ResultData createChoice(ChoiceQuestionForm choiceQuestionForm){
        ResultData result=new ResultData();
        if(StringUtils.isEmpty(choiceQuestionForm.getQuestionTitle())||StringUtils.isEmpty(choiceQuestionForm.getQuestionOption())){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Please make sure you fill all the required fields");
            return result;
        }

        ChoiceQuestion choiceQuestion=new ChoiceQuestion(choiceQuestionForm.getQuestionTitle(),choiceQuestionForm.getQuestionOption());
        ResultData response=choiceQuestionService.create(choiceQuestion);
        if(response.getResponseCode()!=ResponseCode.RESPONSE_OK){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to store choice question");
            return result;
        }
        result.setResponseCode(ResponseCode.RESPONSE_OK);
        result.setData(response.getData());
        result.setDescription("Successful!");
        return result;
    }

}
