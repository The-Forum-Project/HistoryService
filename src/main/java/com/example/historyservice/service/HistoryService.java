package com.example.historyservice.service;

import com.example.historyservice.dao.HistoryDao;
import com.example.historyservice.domain.History;
import com.example.historyservice.dto.HistoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class HistoryService {
    private HistoryDao historyDao;

    @Autowired
    public void setHistoryService(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    @Transactional
    public List<History> getAll() {
        //TODO: add filter by words or date [Bonus]
        return historyDao.getAll();
    }

    @Transactional
    public void add(HistoryRequest request) {
        //TODO: spring security
        //TODO: get userId from token
        Integer userId = 1;

        History history = History.builder()
                .userId(userId)
                .postId(request.getPostId())
                .viewDate(new Date())
                .build();
        historyDao.add(history);
    }
}
