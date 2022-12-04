package com.socialapp.javareact.users;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {

   /*List<User> findByUserNameContaining(String userName);
   User findByUserNameAndDisplayName(String userName, String displayName);*/
    User findByUserName(String userName);

}
