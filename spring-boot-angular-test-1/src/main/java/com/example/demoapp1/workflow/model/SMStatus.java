package com.example.demoapp1.workflow.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SMStatus {

    public Collection<SMState> getStates() {
        return states;
    }

    public String getCurrentRegion() {
        return currentRegion;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public List<String> getEvents() {
        return events;
    }

    public SMStatus(Collection<SMState> states, boolean isFinal) {
        this.states = states;
        this.isFinal = isFinal;
        this.currentRegion = null;
        this.events = new ArrayList<>();
    }


    public SMStatus(Collection<SMState> states, boolean isFinal, String currentRegion) {
        this.states = states;
        this.isFinal = isFinal;
        this.currentRegion = currentRegion;
        this.events = new ArrayList<>();
    }

    public SMStatus(Collection<SMState> states, boolean isFinal, String currentRegion, List<String> events) {
        this.states = states;
        this.isFinal = isFinal;
        this.currentRegion = currentRegion;
        this.events = events;
    }

    private final Collection<SMState> states;
    private final boolean isFinal;
    private final String currentRegion;
    private final List<String> events;
}
