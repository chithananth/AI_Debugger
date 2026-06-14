package com.ai.debugger.controller;

import com.ai.debugger.dto.DebugRequest;
import com.ai.debugger.entity.DebugHistory;
import com.ai.debugger.service.DebugHistoryService;
import com.ai.debugger.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debug")
@CrossOrigin("*")
public class DebugController {

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private DebugHistoryService historyService;

    // AI EXPLAIN API
    @PostMapping("/explain")
    public DebugHistory explainError(
            @RequestBody DebugRequest request) {

        String aiResponse =
                geminiService.explainError(
                        request.getCodeSnippet(),
                        request.getErrorMessage()
                );

        DebugHistory history = new DebugHistory();

        history.setCodeSnippet(
                request.getCodeSnippet());

        history.setErrorMessage(
                request.getErrorMessage());

        history.setAiResponse(aiResponse);

        return historyService.saveDebug(history);
    }

    // GET HISTORY
    @GetMapping
    public List<DebugHistory> getAllDebugs() {

        return historyService.getAllDebugs();
    }
}