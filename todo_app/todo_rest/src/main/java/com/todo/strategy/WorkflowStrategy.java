package com.todo.strategy;

import com.todo.modal.WorkFlowEvent;
import com.todo.modal.WorkFlowItem;

public interface WorkflowStrategy<T, W extends WorkFlowItem<T>> {

    WorkFlowEvent getWorkflowAction();

    void process(W workFlowItem);

    boolean isSupportedWorkflowAction(WorkFlowEvent action);

}
