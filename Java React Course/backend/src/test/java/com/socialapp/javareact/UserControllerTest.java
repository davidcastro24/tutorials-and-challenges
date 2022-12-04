package com.socialapp.javareact;

import com.socialapp.javareact.exceptions.ApiError;
import com.socialapp.javareact.shared.GenericResponse;
import com.socialapp.javareact.users.UserRepository;
import com.socialapp.javareact.users.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.FieldError;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;

    private final String postUser_URI = "/api/v1/users";

    @Before
    public void cleanAll(){
        userRepository.deleteAll();
    }

    @Test
    public void postValidUsertoDatabase(){
        User user = createValidUser();
        postSignUp(user,Object.class);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void postUser_whenUserValidOk(){
        User user = createValidUser();
        ResponseEntity<Object> responseEntity =
                postSignUp(user,Object.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void postUser_whenUserValidGetSuccessMessage(){
        User user = createValidUser();
        ResponseEntity<GenericResponse> responseEntity =
                postSignUp(user,GenericResponse.class);
        assertThat(responseEntity.getBody().getMessage()).isNotNull();
    }

    @Test
    public void postValidUserPasswordHashedinDB(){
        User user = createValidUser();
        postSignUp(user,Object.class);
        List<User> users = userRepository.findAll();
        if (users.size() > 0) {
            User dbUser = users.get(0);
            assertThat(dbUser.getPassword()).isNotEqualTo(user.getPassword());
        }
    }

    @Test
    public void postUser_WhenNullUsernameBadRequest(){
        User user = createValidUser();
        user.setUserName(null);
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenNullDisplayNameBadRequest(){
        User user = createValidUser();
        user.setDisplayName(null);
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenNullPasswordBadRequest(){
        User user = createValidUser();
        user.setPassword(null);
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenLowPasswordSizeBadRequest(){
        User user = createValidUser();
        user.setPassword("abc");
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenLowUsernameSizeBadRequest(){
        User user = createValidUser();
        user.setUserName("a");
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenLowDisplayNameSizeBadRequest(){
        User user = createValidUser();
        user.setDisplayName("a");
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenExceedsPasswordSizeBadRequest(){
        User user = createValidUser();
        user.setPassword(testStringGenerator(300));
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenExceedsUsernameSizeBadRequest(){
        User user = createValidUser();
        user.setUserName(testStringGenerator(150));
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenExceedsDisplayNameSizeBadRequest(){
        User user = createValidUser();
        user.setDisplayName(testStringGenerator(100));
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenPasswordHasAllLowerCaseBadRequest(){
        User user = createValidUser();
        user.setPassword("lowercase");
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenPasswordHasAllUpperCaseBadRequest(){
        User user = createValidUser();
        user.setPassword("LOWERCASE");
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenPasswordHasAllNumbersBadRequest(){
        User user = createValidUser();
        user.setPassword("132456789");
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenInvalidUserReturnAPIError(){
        User user = new User();
        ResponseEntity<ApiError> response = postSignUp(user, ApiError.class);
        assertThat(response.getBody().getValidationErrors().size()).isEqualTo(3);
    }

    @Test
    public void postUser_WhenNullUserNameReturnError(){
        User user = createUserForSpecificCases("displayName",null,"passwordA!@9");
        ResponseEntity<ApiError> response = postSignUp(user, ApiError.class);
        Map<String,String> validationErrors = response.getBody().getValidationErrors();
       /*validationErrors.forEach((key,value) -> {
            System.out.println("----key:"+key+"---val:"+value);
        });
        System.out.println(validationErrors.get("username"));*/
        assertThat(validationErrors.size()).isEqualTo(1);
        assertThat(validationErrors.get("userName")).isNotNull();
    }

    @Test
    public void postUser_WhenNullPasswordReturnError(){
        User user = createUserForSpecificCases("displayName","username",null);
        ResponseEntity<ApiError> response = postSignUp(user, ApiError.class);
        Map<String,String> validationErrors = response.getBody().getValidationErrors();
       /*validationErrors.forEach((key,value) -> {
            System.out.println("----key:"+key+"---val:"+value);
        });
        System.out.println(validationErrors.get("username"));*/
        assertThat(validationErrors.size()).isEqualTo(1);
        assertThat(validationErrors.get("password")).isNotNull();
    }

    @Test
    public void postUser_WhenNullDisplayNameReturnError(){
        User user = createUserForSpecificCases(null,"username","passwordA!@9");
        ResponseEntity<ApiError> response = postSignUp(user, ApiError.class);
        Map<String,String> validationErrors = response.getBody().getValidationErrors();
       /*validationErrors.forEach((key,value) -> {
            System.out.println("----key:"+key+"---val:"+value);
        });
        System.out.println(validationErrors.get("username"));*/
        assertThat(validationErrors.size()).isEqualTo(1);
        assertThat(validationErrors.get("displayName")).isNotNull();
    }

    @Test
    public void postUser_WhenUserNameAlreadyExistsReturnError(){
        userRepository.save(createValidUser());
        User user = createValidUser();
        ResponseEntity<Object> response = postSignUp(user,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_WhenUserNameAlreadyExistsReturnLocalError(){
        userRepository.save(createValidUser());
        User user = createValidUser();
        ResponseEntity<ApiError> response = postSignUp(user,ApiError.class);
        System.out.println(response.getBody().toString());
        assertThat(response.getBody().getValidationErrors().get("userName"))
                .isEqualTo("Username already in use");
    }

    private User createValidUser(){
        User user = new User();
        user.setUserName("test-user");
        user.setDisplayName("TestUser10");
        user.setPassword("Tranquility12103$%!");
        return user;
    }

    private <T> ResponseEntity<T> postSignUp(Object request, Class<T> response){
        return testRestTemplate.postForEntity(postUser_URI,request,response);
    }

    private String testStringGenerator(int size){
        return IntStream.range(1,size).mapToObj(obj -> "s").collect(Collectors.joining());
    }

    private User createUserForSpecificCases(String displayName, String username, String password){
        User user = new User();
        user.setDisplayName(displayName);
        user.setUserName(username);
        user.setPassword(password);
        return user;
    }
}
