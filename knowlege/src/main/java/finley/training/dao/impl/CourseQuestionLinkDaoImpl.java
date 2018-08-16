package finley.training.dao.impl;

import finley.training.dao.BaseDao;
import finley.training.dao.CourseQuestionLinkDao;
import finley.training.model.knowledge.CourseQuestionLink;
import org.springframework.stereotype.Repository;
import util.ResponseCode;
import util.ResultData;

import java.util.List;
import java.util.Map;

@Repository
public class CourseQuestionLinkDaoImpl extends BaseDao implements CourseQuestionLinkDao {

    @Override
    public ResultData query(Map<String, Object> condition) {
        ResultData result = new ResultData();
        try {
            List<CourseQuestionLink> list = sqlSession.selectList("finley.training.courseQuestionLink.query", condition);
            if (list.isEmpty()) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
            result.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData insert(CourseQuestionLink coursequestionLink) {
        ResultData result = new ResultData();
        try {
            sqlSession.insert("finley.training.courseQuestionLink.insert", coursequestionLink);
            result.setData(coursequestionLink);
        } catch (Exception e){
            e.printStackTrace();
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData update(CourseQuestionLink coursequestionlink) {
        ResultData result = new ResultData();
        try {
            sqlSession.update("finley.training.CourseQuestionLink.update", coursequestionlink);
            result.setData(coursequestionlink);
        } catch (Exception e){
            e.printStackTrace();
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }
}

