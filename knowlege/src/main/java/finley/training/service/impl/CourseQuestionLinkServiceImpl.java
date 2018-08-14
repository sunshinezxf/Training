package finley.training.service.impl;

import finley.training.dao.CourseQuestionLinkDao;
import finley.training.model.knowledge.CourseQuestionLink;
import finley.training.service.CourseQuestionLinkService;
import util.ResponseCode;
import util.ResultData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

@Service
public class CourseQuestionLinkServiceImpl implements CourseQuestionLinkService {
    @Autowired
    private CourseQuestionLinkDao courseQuestionLinkDao;

    public ResultData fetch(Map<String, Object> condition) {
        ResultData result = new ResultData();
        ResultData response = courseQuestionLinkDao.query(condition);
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

    @Override
    public ResultData create(CourseQuestionLink courseQuestionLink) {
        ResultData result = new ResultData();
        ResultData response = courseQuestionLinkDao.insert(courseQuestionLink);
        if (response.getResponseCode() != ResponseCode.RESPONSE_OK) {
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription("Fail to store course question link to database");
        } else {
            response.setResponseCode(ResponseCode.RESPONSE_OK);
            result.setData(response.getData());
        }
        return result;
    }
}
