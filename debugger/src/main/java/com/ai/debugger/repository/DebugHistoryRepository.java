package com.ai.debugger.repository;

import com.ai.debugger.entity.DebugHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebugHistoryRepository 
        extends JpaRepository<DebugHistory, Long> {

}