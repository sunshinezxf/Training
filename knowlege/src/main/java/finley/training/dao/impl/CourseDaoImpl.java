package finley.training.dao.impl;

import finley.training.dao.BaseDao;
import finley.training.dao.CourseDao;
import finley.training.model.knowledge.Course;
import org.springframework.stereotype.Repository;
import util.IDGenerator;
import util.ResponseCode;
import util.ResultData;

import java.util.List;
import java.util.Map;

@Repository
public class CourseDaoImpl extends BaseDao implements CourseDao {
    @Override
    public ResultData query(Map<String, Object> condition) {
        ResultData result = new ResultData();
        try {
            List<Course> list = sqlSession.selectList("finley.training.course.query", condition);
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
    public ResultData insert(Course course) {
        ResultData result = new ResultData();
        course.setCourseId(IDGenerator.generate("COU"));
        try {
            sqlSession.insert("finley.training.course.insert", course);
            result.setData(course);
        } catch (Exception e){
            e.printStackTrace();
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }
}
