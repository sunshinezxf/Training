package finley.training.service.impl;

import finley.training.dao.SubjectDao;
import finley.training.service.SubjectService;
import util.ResultData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectDao subjectDao;

    public ResultData fetch(Map<String, Object> condition) {
        return subjectDao.query(condition);
    }
}
