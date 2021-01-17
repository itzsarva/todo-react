package com.todo.service;

import com.todo.exceptions.TodoRestException;
import com.todo.exceptions.StrategyNotFoundException;
import com.todo.modal.WorkFlowEvent;
import com.todo.modal.WorkFlowItem;
import com.todo.strategy.WorkflowStrategy;
import com.google.common.collect.Multimap;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@Service
public class WorkflowPersistService {

    //@Resource
    Multimap<WorkFlowEvent, WorkflowStrategy> getWorkflowStrategies;

    public void execute(WorkFlowItem wfi, WorkFlowEvent event) throws StrategyNotFoundException, TodoRestException {
        try {
            Collection<WorkflowStrategy> strategyList = getWorkflowStrategies.get(event);
            if (CollectionUtils.isEmpty(strategyList)) {
                throw new StrategyNotFoundException(new StringBuilder().append("No Strategies Found for Event ").append(event.name()).toString());
            } else {
                for (WorkflowStrategy strategy : strategyList) {
                    strategy.process(wfi);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new TodoRestException("Error Saving the Data");
        }
    }
}

