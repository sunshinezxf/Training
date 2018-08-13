package finley.training.dao.impl;

import finley.training.dao.BaseDao;
import finley.training.dao.SubjectDao;
import finley.training.model.knowledge.Subject;
import org.springframework.stereotype.Repository;
import util.IDGenerator;
import util.ResponseCode;
import util.ResultData;

import java.util.List;
import java.util.Map;

@Repository
public class SubjectDaoImpl extends BaseDao implements SubjectDao {

    @Override
    public ResultData query(Map<String, Object> condition) {
        ResultData result = new ResultData();
        try {
            List<Subject> list = sqlSession.selectList("finley.training.subject.query", condition);
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
    public ResultData insert(Subject subject) {
        ResultData result = new ResultData();
        subject.setSubjectId(IDGenerator.generate("SUB"));
        try {
            sqlSession.insert("finley.training.subject.insert", subject);
            result.setData(subject);
        } catch (Exception e){
            e.printStackTrace();
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData update(Subject subject) {
        ResultData result = new ResultData();
        try {
            sqlSession.update("finley.training.subject.update", subject);
            result.setData(subject);
        } catch (Exception e){
            e.printStackTrace();
            result.setResponseCode(ResponseCode.RESPONSE_ERROR);
            result.setDescription(e.getMessage());
        }
        return result;
    }
}
