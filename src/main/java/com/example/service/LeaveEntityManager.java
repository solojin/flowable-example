package com.example.service;

import com.example.entity.LeaveEntity;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Service
public class LeaveEntityManager {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public LeaveEntity newLeave(DelegateExecution execution) {
        LeaveEntity leave = new LeaveEntity();
        leave.setProcessInstanceId(execution.getProcessInstanceId());
        leave.setUserId(execution.getVariable("applyUserId").toString());
        leave.setStartTime((Date)execution.getVariable("startTime"));
        leave.setEndTime((Date)execution.getVariable("endTime"));
        leave.setReason(execution.getVariable("reason").toString());
        entityManager.persist(leave);
        return leave;
    }
}
