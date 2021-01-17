package com.todo.validation;

import com.todo.modal.Register;
import com.todo.modal.WorkFlowItem;
import com.todo.validations.Validation;
import com.todo.validations.ValidationResult;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class EmailIdValidation {

/*    @Autowired
    UserService userDetailsService;*/

    @Value("${error.email}")
    private String validationMessage;

    private Boolean isValidEmailId(Register wfi) {
        //return userDetailsService.findUserByEmail(wfi.getUser().getEmailId()) == null;
        return true;
    }

    @Getter
    public Validation<WorkFlowItem<Register>> emailIdValidator = (WorkFlowItem<Register> registerWfi) ->
    {
        Boolean val;
        val = Stream.of(registerWfi.getData()).map(register -> isValidEmailId(register)).allMatch(isValid -> isValid == Boolean.TRUE);
        return new ValidationResult(val, validationMessage);
    };
}
