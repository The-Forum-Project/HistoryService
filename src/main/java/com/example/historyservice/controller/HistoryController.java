package com.example.historyservice.controller;

import com.example.historyservice.domain.History;
import com.example.historyservice.dto.HistoryRequest;
import com.example.historyservice.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
@RestController
public class HistoryController {
    private HistoryService historyService;

    @Autowired
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/histories")
    public List<History> getAllHistories(){
        return historyService.getAll();
    }

    @PostMapping("/histories")
    public void addHistory(@Valid @RequestBody HistoryRequest request){
        historyService.add(request);
    }
}
