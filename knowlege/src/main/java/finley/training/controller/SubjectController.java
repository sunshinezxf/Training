package finley.training.controller;

import finley.training.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.ResponseCode;
import util.ResultData;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/knowledge/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResultData getSubject() {
        ResultData result = new ResultData();
        Map<String, Object> condition = new HashMap<>();
        condition.put("blockFlag", false);
        condition.put("subjectId", "1");
        ResultData response = subjectService.fetch(condition);
        if (response.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
            result.setDescription("No subjuct found from database");
        }
        if (response.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to retrieve subject information from database");
        }
        if (response.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_OK);
            result.setData(response.getData());
        }
        return result;
    }
}