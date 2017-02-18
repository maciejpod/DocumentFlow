/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.validator;

import net.podolanski.dto.UserForm;
import net.podolanski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author maciej
 */
@Component
public class UserFormValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> type) {
        return UserForm.class.equals(type);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        UserForm userForm = (UserForm) obj;

        if (!userService.emailExists(userForm.getEmail())) {
            errors.rejectValue("email", "emailExists");
        }

    }

}
