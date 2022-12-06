package com.springmvc.demo.classes;

import com.springmvc.demo.validations.PersonSubject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class Person {

    @NotBlank(message = "Required")
    private String firstName;

    @NotBlank(message = "Required")
    private String lastName;

    @NotNull(message = "Required")
    @Range(min = 18, max = 99)
    private Integer age;

    @NotBlank(message = "Required")
    private String country;

    @Length(max=150)
    @Pattern(regexp = "^[a-zA-z]+",message = "Must only contain letters")
    @PersonSubject
    private String description;

    private String[] preferredOS;

    @NotBlank(message = "Required")
    private String language;

    public String getCountryName(){
        return new DataForDisplayedLists().getCountryFromISOCode(this.country);
    }

    public String getLanguageName(){
        return new DataForDisplayedLists().getLanguageFromCode(this.language);
    }

    /*public Person() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreferredOS() {
        return preferredOS;
    }

    public void setPreferredOS(String preferredOS) {
        this.preferredOS = preferredOS;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                '}';
    }*/
}
