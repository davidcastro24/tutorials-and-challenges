package com.socialapp.javareact.users;

import com.socialapp.javareact.errors.DuplicateUserNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    //@Autowired//field injection spring
    UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //constructor injector
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        //check if username exists
        /*Optional<User> userInDB = Optional.of(userRepository.findByUserName(user.getUserName()));
        if (userInDB.isPresent())
            throw new DuplicateUserNameException();*/
        //encode password with spring security
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
