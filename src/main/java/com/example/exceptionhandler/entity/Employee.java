package com.example.exceptionhandler.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@SequenceGenerator(name = "employee_seq", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
public class Employee {


    @Id
    @GeneratedValue(generator = "employee_seq")
    private int id;

    @Column(unique = true)
    @NotBlank(message = "FirstName cannot be blank")
    private String firstName;

    @Column(unique = true)
    @NotEmpty(message = "lastName cannot be empty")
    private String lastName;

    @Size(min = 8, max = 15, message = "email should at least 8 characters")
    private String email;

    private double salary;

    public Employee(String firstName, String lastName, String email, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
    }

}


