package finley.training.controller;

import com.ctc.wstx.util.StringUtil;
import finley.training.service.ChoiceQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.ResponseCode;
import util.ResultData;

import javax.ws.rs.core.Request;
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


}
