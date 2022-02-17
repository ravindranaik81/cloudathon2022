package com.example.demoapp1.workflow.core;

import com.example.demoapp1.workflow.model.SMStatus;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class WorkflowTaskManager implements InitializingBean {

    @Value("${workflow-config}")
    private String workflowConfig;

    private StateMachine stateMachine;

    public WorkflowTaskManager() {
    }

    @PostConstruct
    void init(){
        StateMachine stateMachine = new StateMachine(workflowConfig);
        stateMachine.initializeStateMachine();
        this.stateMachine = stateMachine;
        //Temp testing
        //applyEvent("PrimaryOpsReviewReject");
        //applyEvent("PrimaryOpsReviewApprove");
    }

    public StateMachine getStateMachine() {
        return this.stateMachine;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if(stateMachine == null){
            throw new IllegalArgumentException();
        }
    }

    public List<String> applyEvent(String[] startingState, String event){
        //Temp testing
        //String[] startingState = new String[1];
        //startingState[0] = "PrimaryOpsReview-Pending";
        SMStatus sm = this.stateMachine.applyEvent(startingState, event);
        // Put debug here
        List<String> targetStateList = new ArrayList<>();
        sm.getStates().stream().forEach(state -> {
            System.out.println(state.toString());
            targetStateList.add(state.getStateId());
        });
        return targetStateList;
    }
}
