package finley.training.controller;

import finley.training.service.ChoiceQuestionService;
import finley.training.service.CourseQuestionLinkService;
import finley.training.service.CourseService;
import finley.training.service.SubjectService;
import form.knowledge.SubjectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.ResultData;

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

        return result;
    }

    @PostMapping(value = "/subject/create")
    public ResultData createSubject(SubjectForm form) {
        ResultData result = new ResultData();

        return result;
    }
}
