package com.AccountsAPI.demo.SimpleCustomer;


import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Customer")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private Integer CustomerCode;
    @NonNull
    private String FirstName;
    @NonNull
    private String LastName;
    @NonNull
    @Column( unique = true,nullable = false)
    private Integer PhoneNumber;
    @NonNull
    @Column(unique = true,nullable = false)
    private String Email;
    @NonNull
    private String Nationality;
    @NonNull
    @Column(unique = true,nullable = false)
    private String customerId;
    @NonNull
    private String IdType;
    //Optional Fields
    private String MaritalStatus;
    private String Language;
    private String Job;

}
