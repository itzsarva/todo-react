package com.todo.enrichment;

import com.todo.modal.WorkFlowEvent;
import com.todo.modal.WorkFlowItem;
import org.springframework.stereotype.Service;

@Service
public interface WorkflowEnrichment<T> {

    void enrich(WorkFlowItem<T> wfi);

    WorkFlowEvent getWorkflowAction();

}
