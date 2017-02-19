/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.validator;

import net.podolanski.dto.PasswordChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author maciej
 */
@Component
public class PasswordValidator implements Validator {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> type) {
        return PasswordChange.class.equals(type);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PasswordChange passwordChange = (PasswordChange) obj;

        if (!passwordEncoder.matches(passwordChange.getOldPassword(),
                passwordChange.getUserPassword())) {
            errors.rejectValue("oldPassword", "wrongPassword", "Invalid password!");
        }

        if (!passwordChange.getNewPassword().equals(passwordChange.getRepeatedPassword())) {
            errors.rejectValue("repeatedPassword", "notSamePasswords",
                    "Password and Confirmation password do not match!");
        }
    }
}
