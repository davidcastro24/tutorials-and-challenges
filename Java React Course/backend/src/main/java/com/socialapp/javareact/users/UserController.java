package com.socialapp.javareact.users;

import com.socialapp.javareact.exceptions.ApiError;
import com.socialapp.javareact.exceptions.UserNotValidException;
import com.socialapp.javareact.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
//no business logic, only (http req,resp)
    @Autowired
    UserService userService;

    @PostMapping("/api/v1/users")
    public GenericResponse createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return new GenericResponse("User Saved");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request){
        BindingResult result = exception.getBindingResult();
        Map<String,String> validationErrors = new HashMap<>();
        for (FieldError fieldError: result.getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ApiError apiError = new ApiError(400,"Validation error in body",request.getServletPath());
        apiError.setValidationErrors(validationErrors);
        return apiError;
    }


}
