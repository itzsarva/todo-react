package com.todo.strategy;

import com.todo.modal.Register;
import com.todo.modal.WorkFlowEvent;
import com.todo.modal.WorkFlowItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NewWorkflowStrategy implements WorkflowStrategy<Register, WorkFlowItem<Register>> {


    @Autowired
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public WorkFlowEvent getWorkflowAction() {
        return WorkFlowEvent.NEW;
    }

    @Override
    public void process(WorkFlowItem<Register> workFlowItem) {
       /* userRepository.saveAll(workFlowItem.getData().updatableList().stream()
                .map(User.class::cast)
                .map(encodePassword.apply(bCryptPasswordEncoder))
                .map(auditUser.get())
                .collect(Collectors.toList()));*/
    }


    @Override
    public boolean isSupportedWorkflowAction(WorkFlowEvent action) {
        return (action == WorkFlowEvent.NEW || action == WorkFlowEvent.UPDATE);
    }
}
