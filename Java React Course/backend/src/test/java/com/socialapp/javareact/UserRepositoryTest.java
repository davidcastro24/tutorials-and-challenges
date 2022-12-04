package com.socialapp.javareact;

import com.socialapp.javareact.users.User;
import com.socialapp.javareact.users.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest //init only jpa config
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager; //alternative to standard jpa entity manager (testing purposes)

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUserName_IfExistsReturnUser(){
        User user = createValidUser();
        testEntityManager.persist(user);
        User inDB = userRepository.findByUserName(user.getUserName());
        assertThat(inDB).isNotNull();

    }

    @Test
    public void findByUserName_IfNotExistsReturnNull(){
        User user = createValidUser();
        testEntityManager.persist(user);
        User inDB = userRepository.findByUserName("blabla");
        assertThat(inDB).isNull();

    }

    private User createValidUser(){
        User user = new User();
        user.setUserName("test-user");
        user.setDisplayName("TestUser10");
        user.setPassword("Tranquility12103$%!");
        return user;
    }
}
