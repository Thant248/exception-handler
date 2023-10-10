package com.example.exceptionhandler.repository;

import com.example.exceptionhandler.entity.Employee;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo  extends JpaRepositoryImplementation<Employee, Integer> {
}
