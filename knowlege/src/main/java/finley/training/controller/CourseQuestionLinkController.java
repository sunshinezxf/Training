package finley.training.controller;

import finley.training.service.CourseQuestionLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.ResponseCode;
import util.ResultData;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/knowledge/courseQuestionLink")
public class CourseQuestionLinkController {
    @Autowired
    private CourseQuestionLinkService  courseQuestionLinkService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResultData getCourseQuestionLink() {
        ResultData result = new ResultData();
        Map<String, Object> condition = new HashMap<>();
        condition.put("blockFlag", false);
        ResultData response = courseQuestionLinkService.fetch(condition);
        if (response.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
            result.setDescription("No course question link found from database");
        }
        if (response.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to retrieve course question link information from database");
        }
        if (response.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_OK);
            result.setData(response.getData());
        }
        return result;
    }
}
