package com.example.demoapp1.workflow.model;

import java.util.Set;


public class SMState {
    @Override
    public String toString() {
        return "SMState{" +
                "stateId='" + stateId + '\'' +
                ", stepId='" + stepId + '\'' +
                ", isFinal=" + isFinal +
                ", transitionEvents=" + transitionEvents +
                ", currentStepState='" + currentStepState + '\'' +
                '}';
    }

    public SMState(){}
    public SMState(String stateId, Set<String> transitionEvents){
        this.stateId=stateId;
        this.transitionEvents=transitionEvents;
        setState(stateId);
    }

    private void setState(String stateId){

        if(stateId != null){
            String[] stepStates = stateId.split("\\-", -1);
            this.stepId = stepStates[0];
            this.currentStepState = stepStates[1];
        }
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
        setState(stateId);
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public Set<String> getTransitionEvents() {
        return transitionEvents;
    }

    public void setTransitionEvents(Set<String> transitionEvents) {
        this.transitionEvents = transitionEvents;
    }

    public String getCurrentStepState() {
        return currentStepState;
    }

    public void setCurrentStepState(String currentStepState) {
        this.currentStepState = currentStepState;
    }
    private String stateId;
    private String stepId;
    private boolean isFinal;
    private Set<String> transitionEvents;
    private String currentStepState;

}
