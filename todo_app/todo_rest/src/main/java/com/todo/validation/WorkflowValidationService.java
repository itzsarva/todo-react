package com.todo.validation;

import com.todo.modal.WorkFlowEvent;
import com.todo.modal.WorkFlowItem;
import com.todo.util.Updatable;
import com.todo.validations.Validation;
import com.google.common.collect.Multimap;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public abstract class WorkflowValidationService<W extends WorkFlowItem<U>, U extends Updatable> {

    public abstract List<WorkFlowEvent> getWorkflowAction();

    public abstract Multimap<WorkFlowEvent, Validation> getWorkFlowValidationMap(W wfi);

}
