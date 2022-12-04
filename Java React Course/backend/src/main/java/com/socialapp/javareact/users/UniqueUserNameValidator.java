package com.socialapp.javareact.users;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName,String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String valueUserName, ConstraintValidatorContext context) {
        //Optional<User> userInDB = Optional.of(userRepository.findByUserName(valueUserName));
        if(userRepository.findByUserName(valueUserName) == null){
            return true;
        }
        return false;
    }
}
