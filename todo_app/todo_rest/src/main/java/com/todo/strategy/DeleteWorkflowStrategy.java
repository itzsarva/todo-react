package com.todo.strategy;

import com.todo.modal.Register;
import com.todo.modal.WorkFlowEvent;
import com.todo.modal.WorkFlowItem;

public class DeleteWorkflowStrategy implements WorkflowStrategy<Register, WorkFlowItem<Register>> {

    @Override
    public WorkFlowEvent getWorkflowAction() {
        return WorkFlowEvent.DELETE;
    }

    @Override
    public boolean isSupportedWorkflowAction(WorkFlowEvent action) {
        return action == WorkFlowEvent.DELETE;
    }

    @Override
    public void process(WorkFlowItem<Register> workFlowItem) {
    }
}
