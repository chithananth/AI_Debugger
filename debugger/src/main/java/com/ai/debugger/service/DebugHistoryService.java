package com.ai.debugger.service;

import com.ai.debugger.entity.DebugHistory;
import com.ai.debugger.repository.DebugHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebugHistoryService {

    @Autowired
    private DebugHistoryRepository repository;

    // SAVE DATA
    public DebugHistory saveDebug(DebugHistory debugHistory) {
        return repository.save(debugHistory);
    }

    // GET ALL DATA
    public List<DebugHistory> getAllDebugs() {
        return repository.findAll();
    }
}