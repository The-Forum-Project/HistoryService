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
    public List<History> getAllByUserId() {
        //TODO : add filter by words or date [Bonus]
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();
        List<History> all = historyDao.getAllByUserId(userId);
        return all;
    }

    @Transactional
    public void add(HistoryRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();

        List<History> histories = historyDao.getAllByUserId(userId);
        if (!histories.isEmpty()) {
            History lastHistory = histories.get(0);
            if (lastHistory.getPostId().equals(request.getPostId())) {
                // If the last history has the same postId, do not add a new history
                return;
            }
        }

        History history = History.builder()
                .userId(userId)
                .postId(request.getPostId())
                .viewDate(new Date())
                .build();
        historyDao.add(history);
    }
}
