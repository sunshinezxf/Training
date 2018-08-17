package finley.training.controller;

import finley.training.model.knowledge.ChoiceQuestion;
import finley.training.model.knowledge.Course;
import finley.training.model.knowledge.CourseQuestionLink;
import finley.training.model.knowledge.Subject;
import finley.training.service.ChoiceQuestionService;
import finley.training.service.CourseQuestionLinkService;
import finley.training.service.CourseService;
import finley.training.service.SubjectService;
import form.knowledge.ChoiceQuestionForm;
import form.knowledge.CourseForm;
import form.knowledge.CourseQuestionForm;
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


    //查询科目表
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

    //传入相应字段数据创建subject记录
    @PostMapping(value = "/subject/create")
    public ResultData createSubject(SubjectForm subjectForm) {
        ResultData result = new ResultData();
        if(StringUtils.isEmpty(subjectForm.getSubjectName())||StringUtils.isEmpty(subjectForm.getSubjectLink())){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Please check your data not null");
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


    //查询course和question的连接表
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

    //传入相应字段数据创建course question连接表的记录
    @PostMapping(value = "/courseQuestionLink/create")
    public ResultData createCQLink(CourseQuestionForm courseQuestionForm){
        ResultData result=new ResultData();
        if(StringUtils.isEmpty(courseQuestionForm.getCourseId())||StringUtils.isEmpty(courseQuestionForm.getQuestionId())){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Please check your data not null");
            return result;
        }
        CourseQuestionLink courseQuestionLink=new CourseQuestionLink(courseQuestionForm.getCourseId(),courseQuestionForm.getCourseId());
        ResultData response=courseQuestionLinkService.create(courseQuestionLink);
        if(response.getResponseCode()!=ResponseCode.RESPONSE_OK){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to store course question link");
            return  result;
        }
        result.setResponseCode(ResponseCode.RESPONSE_OK);
        result.setData(response.getData());
        return result;
    }

    //查询知识点表
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

    //传入相应字段数据创建course记录
    @PostMapping(value = "/course/create")
    public ResultData createCourse(CourseForm courseForm) {
        ResultData result = new ResultData();
        if(StringUtils.isEmpty(courseForm.getCourseDescription())|| StringUtils.isEmpty(courseForm.getSubjectId())) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Please check your data not null");
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

    //查询choiceQuestion表
    @GetMapping(value = "/choiceQuestion/list")
    public ResultData queryChoiceQuestion(){
        ResultData result=new ResultData();
        Map<String,Object> condition=new HashMap<>();
        condition.put("blockFlag",false);
        ResultData response=choiceQuestionService.fetch(condition);
        if(response.getResponseCode()==ResponseCode.RESPONSE_NULL){
            result.setResponseCode(ResponseCode.RESPONSE_NULL);
            result.setDescription("No choice_question found from database");
        }
        if (response.getResponseCode()==ResponseCode.RESPONSE_ERROR){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to retrieve choice_question information from database");
        }
        if(response.getResponseCode()==ResponseCode.RESPONSE_OK){
            result.setResponseCode(ResponseCode.RESPONSE_OK);
            result.setData(response.getData());
        }
        return result;

    }

    //传入相应字段数据创建choiceQuestion记录
    @PostMapping(value = "/choiceQuestion/create")
    public ResultData createChoiceQuestion(ChoiceQuestionForm choiceQuestionForm){
        ResultData result=new ResultData();
        if(StringUtils.isEmpty(choiceQuestionForm.getQuestionOption())||StringUtils.isEmpty(choiceQuestionForm.getQuestionTitle())){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("please check your data not null");
            return result;
        }
        ChoiceQuestion choiceQuestion=new ChoiceQuestion(choiceQuestionForm.getQuestionTitle(),choiceQuestionForm.getQuestionOption());
        ResultData response=choiceQuestionService.create(choiceQuestion);
        if(response.getResponseCode()!=ResponseCode.RESPONSE_OK){
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to store choice_question");
            return  result;
        }
        result.setResponseCode(ResponseCode.RESPONSE_OK);
        result.setData(response.getData());
        return result;
    }
}
