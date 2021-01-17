package com.todo.enrichment;

import com.todo.modal.WorkFlowEvent;
import com.todo.modal.WorkFlowItem;

public class NewWorkflowEnrichment<T> implements WorkflowEnrichment<T> {

    public void enrich(WorkFlowItem<T> wfi) {

    }

    public WorkFlowEvent getWorkflowAction() {
        return WorkFlowEvent.NEW;
    }
}
