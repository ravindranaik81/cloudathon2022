package com.example.demoapp1.workflow.core;

import com.example.demoapp1.workflow.model.SMState;
import com.example.demoapp1.workflow.model.SMStatus;
import org.apache.commons.scxml.SCXMLExecutor;
import org.apache.commons.scxml.Status;
import org.apache.commons.scxml.TriggerEvent;
import org.apache.commons.scxml.env.SimpleDispatcher;
import org.apache.commons.scxml.env.SimpleErrorReporter;
import org.apache.commons.scxml.env.jexl.JexlContext;
import org.apache.commons.scxml.env.jexl.JexlEvaluator;
import org.apache.commons.scxml.io.SCXMLParser;
import org.apache.commons.scxml.model.*;
import org.springframework.beans.factory.InitializingBean;

import java.net.URL;
import java.util.*;

public class StateMachine implements InitializingBean {

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    private String config;
    private SCXML stateMachine;

    public StateMachine(String config) {
        this.config = config;
    }

    public StateMachine() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (config == null) {
            throw new IllegalArgumentException();
        }
        initializeStateMachine();
    }

    public void initializeStateMachine() {
        try {
            URL url = StateMachine.class.getClassLoader().getResource(this.config);
            stateMachine = SCXMLParser.parse(url.toString(), null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SMState getInitialState() {
        String initialState = this.stateMachine.getInitial();
        return new SMState(initialState, null);
    }

    public SMStatus applyEvent(String[] startingState, String event) {

        SMStatus smStatus = null;
        SCXMLExecutor executor = createExecutor(this.stateMachine, startingState);
        if (executor != null && event != null) {
            if (getTransition(executor.getCurrentStatus()).contains(event)) {
                smStatus = applyEvent(executor, event);
            }
        }
        return smStatus;
    }

    private SMStatus applyEvent(SCXMLExecutor executor, String event) {
        TriggerEvent[] triggerEvents = createSignalEvents(new String[]{event});
        try {
            executor.triggerEvents(triggerEvents);
        } catch (ModelException e) {
            throw new RuntimeException(e);
        }

        SMStatus smStatus = convertStatus(executor.getCurrentStatus());
        return smStatus;
    }

    private SMStatus convertStatus(Status currentStatus) {
        Set<State> states = currentStatus.getStates();
        Set<SMState> smStates = new HashSet<>(states.size());
        String currRegion = null;
        for (State state : states) {
            if (state != null) {
                SMState smState = new SMState();
                smState.setStateId(state.getId());
                smState.setTransitionEvents(new HashSet<>(5));
                smState.setFinal(state.isFinal());
                if (currRegion == null) {
                    currRegion = determineParallelId(state);
                }
                List<Transition> transitions = state.getTransitionsList();
                if (transitions != null) {
                    for (Transition transition : transitions) {
                        if (transition.getEvent() != null) {
                            smState.getTransitionEvents().add(transition.getEvent());
                        }
                    }
                }
                smStates.add(smState);
            }
        }
        boolean isFinal = currentStatus.isFinal();
        return new SMStatus(smStates, isFinal, currRegion);

    }

    private String determineParallelId(State state) {
        String parallelId = null;
        if (state.isRegion()) {
            parallelId = state.getParent().getId();
        } else if ((state.getParent() != null) && (state.getParent().getParent() != null) && (state.getParent().getParent() instanceof Parallel)) {
            parallelId = ((Parallel) state.getParent().getParent()).getId();
        }
        return parallelId;
    }

    private TriggerEvent[] createSignalEvents(String... events) {
        List<TriggerEvent> eventList = new ArrayList<>(events.length);
        for (String event : events) {
            eventList.add(new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT));

        }
        return eventList.toArray(new TriggerEvent[0]);
    }


    private Set<String> getTransition(Status currentStatus) {
        Set<String> transitions = new HashSet<>();
        Set allStates = currentStatus.getAllStates();

        allStates.stream().forEach(
                curr -> {
                    if (curr instanceof State) {
                        State state = (State) curr;
                        if (state != null) {
                            List<Transition> transitionList = state.getTransitionsList();
                            transitionList.stream()
                                    .map(Transition::getEvent)
                                    .filter(Objects::nonNull).forEachOrdered(transitions::add);
                        }
                    } else if (curr instanceof Parallel) {
                        Parallel parallel = (Parallel) curr;
                        if (parallel != null) {
                            List<Transition> transitionList = parallel.getTransitionsList();
                            transitionList.stream()
                                    .map(Transition::getEvent)
                                    .filter(Objects::nonNull).forEachOrdered(transitions::add);
                        }
                    }
                }
        );

        return transitions;
    }

    private SCXMLExecutor createExecutor(SCXML stateMachine, String[] startingState) {
        SCXMLExecutor executor = new SCXMLExecutor(new JexlEvaluator(), new SimpleDispatcher(), new SimpleErrorReporter());
        executor.setStateMachine(stateMachine);
        executor.setSuperStep(true);
        executor.setRootContext(new JexlContext());
        try {
            executor.go();
        } catch (ModelException e) {
            throw new RuntimeException(e);
        }

        if (startingState != null && startingState.length > 0) {
            updateStateMachineStates(startingState, executor);
        }

        return executor;

    }

    private void updateStateMachineStates(String[] startingState, SCXMLExecutor executor) {
        Set<TransitionTarget> states = executor.getCurrentStatus().getStates();
        states.clear();
        Arrays.stream(startingState)
                .map(executor.getStateMachine().getTargets()::get)
                .map(TransitionTarget.class::cast)
                .filter(Objects::nonNull)
                .forEachOrdered(states::add);
    }


}
