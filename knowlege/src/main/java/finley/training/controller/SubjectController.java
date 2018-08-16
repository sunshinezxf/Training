package finley.training.controller;

import finley.training.model.knowledge.Subject;
import finley.training.service.SubjectService;
import form.knowledge.SubjectForm;
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
@RequestMapping("/knowledge/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResultData getSubject() {
        ResultData result = new ResultData();
        Map<String, Object> condition = new HashMap<>();
        condition.put("blockFlag", false);
        ResultData response = subjectService.fetch(condition);
        if (response.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
            result.setDescription("No subject found from database");
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

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResultData createSubject(SubjectForm form) {
        ResultData result = new ResultData();
        if (StringUtils.isEmpty(form.getSubjectLink()) || StringUtils.isEmpty(form.getSubjectName())) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Please make sure you fill all the required fields");
            return result;
        }

        Subject subject = new Subject(form.getSubjectName(), form.getSubjectLink());
        ResultData response = subjectService.create(subject);
        if (response.getResponseCode() != ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to store subject");
            return result;
        }
        result.setResponseCode(ResponseCode.RESPONSE_OK);
        result.setData(response.getData());
        result.setDescription("Successful!");
        return result;
    }
}
