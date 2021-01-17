package com.todo.validation;

import com.todo.modal.Register;
import com.todo.modal.WorkFlowEvent;
import com.todo.modal.WorkFlowItem;
import com.todo.validations.Validation;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RegisterWorkflowValidationImpl extends WorkflowValidationService<WorkFlowItem<Register>, Register> {

    @Autowired
    UsernameValidation usernameValidation;

    @Autowired
    EmailIdValidation emailIdValidation;

    @Override
    public List<WorkFlowEvent> getWorkflowAction() {
        return Arrays.asList(WorkFlowEvent.NEW);
    }

    @Override
    public Multimap<WorkFlowEvent, Validation> getWorkFlowValidationMap(WorkFlowItem<Register> wfi) {
        Multimap<WorkFlowEvent, Validation> validationMap = HashMultimap.create();
        validationMap.put(WorkFlowEvent.NEW, usernameValidation.getUserNameValidator());
        validationMap.put(WorkFlowEvent.NEW, emailIdValidation.getEmailIdValidator());
        return validationMap;
    }
}
