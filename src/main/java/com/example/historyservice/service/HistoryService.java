package com.example.historyservice.service;

import com.example.historyservice.dao.HistoryDao;
import com.example.historyservice.domain.History;
import com.example.historyservice.dto.HistoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
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
        //TODO : add filter by words or date [Bonus]
        List<History> all = historyDao.getAll();
        Collections.reverse(all);
        return all;
    }

    @Transactional
    public void add(HistoryRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        History history = History.builder()
                .userId(userId)
                .postId(request.getPostId())
                .viewDate(new Date())
                .build();
        historyDao.add(history);
    }
}
