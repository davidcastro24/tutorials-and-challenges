package com.socialapp.javareact.users;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="users")//, uniqueConstraints = @UniqueConstraint(columnNames = "userName")) create unique columns in db
public class User {

    @Id
    @GeneratedValue
    private long id;

    @NotNull//(message = "{javareact.constraints.username.NotNull.Message}")
    @Size(min = 4, max = 50)
    @UniqueUserName
    private String userName;
    @NotNull
    @Size(min = 10, max = 50)
    private String displayName;
    @NotNull
    @Size(min = 8,max=255)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-.]).{8,}$",
            message = "Password must contain at least one upper case letter, one lower case letter," +
                    "one number and one special character")
    private String password;

}
