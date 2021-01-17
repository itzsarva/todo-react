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
public class UsernameValidation {

/*    @Autowired
    UserDetailsService userDetailsService;*/

    @Value("${error.username}")
    private String validationMessage;

    private Boolean isValidUserName(Register wfi) {
        //Optional<UserDetails> userDetails =  userDetailsService.findByUsername(wfi.getUser().getUserDetails().getUsername());
        return true;
    }

    @Getter
    public Validation<WorkFlowItem<Register>> userNameValidator = (WorkFlowItem<Register> registerWfi) ->
    {
        Boolean val;
        val = Stream.of(registerWfi.getData()).map(register -> isValidUserName(register)).allMatch(isValid -> isValid == Boolean.TRUE);
        return new ValidationResult(val, validationMessage);
    };

}
