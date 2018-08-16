package finley.training.controller;

import finley.training.model.knowledge.Course;
import finley.training.model.knowledge.Subject;
import finley.training.service.ChoiceQuestionService;
import finley.training.service.CourseQuestionLinkService;
import finley.training.service.CourseService;
import finley.training.service.SubjectService;
import form.knowledge.CourseForm;
import form.knowledge.SubjectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import util.ResponseCode;
import util.ResultData;

import java.util.HashMap;
import java.util.Map;

/**
 * Each domain is regarded as a specific knowledge
 */
@RestController
@RequestMapping("/knowledge")
public class DomainController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChoiceQuestionService choiceQuestionService;

    @Autowired
    private CourseQuestionLinkService courseQuestionLinkService;

    @GetMapping(value = "/subject/list")
    public ResultData querySubject() {
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

    @PostMapping(value = "/subject/create")
    public ResultData createSubject(SubjectForm subjectForm) {
        ResultData result = new ResultData();
        if(StringUtils.isEmpty(subjectForm.getSubjectName())||StringUtils.isEmpty(subjectForm.getSubjectLink())){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Please check your data");
            return result;
        }
        Subject subject=new Subject(subjectForm.getSubjectName(),subjectForm.getSubjectLink());
        ResultData response=subjectService.create(subject);
        if(response.getResponseCode()!=ResponseCode.RESPONSE_OK){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to store subject");
            return result;
        }
        result.setResponseCode(ResponseCode.RESPONSE_OK);
        result.setData(response.getData());
        return result;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/courseQuestionLink/list")
    public ResultData queryCourseQuestionLink() {

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


    @RequestMapping(method = RequestMethod.GET, value = "/course/list")
    public ResultData queryCourse() {

        ResultData result = new ResultData();
        Map<String, Object> condition = new HashMap<>();
        condition.put("blockFlag", false);
        ResultData response = courseService.fetch(condition);
        if (response.getResponseCode() == ResponseCode.RESPONSE_NULL) {
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
            result.setDescription("No course found from database");
        }
        if (response.getResponseCode() == ResponseCode.RESPONSE_ERROR) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to retrieve course information from database");
        }
        if (response.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_OK);
            result.setData(response.getData());
        }
        return result;
    }

    @PostMapping(value = "/course/create")
    public ResultData createCourse(CourseForm courseForm) {
        ResultData result = new ResultData();
        if(StringUtils.isEmpty(courseForm.getCourseDescription())|| StringUtils.isEmpty(courseForm.getSubjectId())) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Please check your data");
            return result;
        }
        Course course = new Course (courseForm.getSubjectId(),courseForm.getCourseDescription());
        ResultData response = courseService.create(course);
        if (response.getResponseCode()!=ResponseCode.RESPONSE_OK){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to store subject");
            return result;
        }
        result.setResponseCode(ResponseCode.RESPONSE_OK);
        result.setData(response.getData());
        return result;
    }

}
