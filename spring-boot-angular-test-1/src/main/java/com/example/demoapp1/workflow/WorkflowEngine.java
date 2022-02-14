package com.example.demoapp1.workflow;

import com.example.demoapp1.workflow.core.WorkflowTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkflowEngine {

    @Autowired
    private WorkflowTaskManager workflowTaskManager;
}
