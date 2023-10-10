package com.example.exceptionhandler.api;

import com.example.exceptionhandler.entity.Employee;
import com.example.exceptionhandler.expection.NameAdminException;
import com.example.exceptionhandler.repository.EmployeeRepo;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("employee")
public class EmployeeApi {

    @Autowired
    private EmployeeRepo repo;

    record  EmployeeResponse (int id, @JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName,
                              String email,double salary){}

    record  EmployeeForm( @JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName,
                          String email,double salary){}


    @GetMapping("create")
    public String creation(){
        List.of(

                new Employee(1,"John", "Doe", "john@gmail.com", 10000),
                new Employee(2,"Thomas", "Hadry", "thomas@gmail.com", 10000)
        ).stream().forEach(repo::save);
        return "successfully created";
    }

    @GetMapping("{id}")
    public Employee findEmployeeById(@PathVariable int id){

        return  repo.findById(id).orElseThrow(()-> new EntityNotFoundException("not found"));
    }

    @PostMapping
    public  EmployeeResponse create( @RequestBody  @Valid EmployeeForm employee){
        if ("admin".equalsIgnoreCase(employee.firstName)){
            throw new NameAdminException();
        }
        return  toDto(repo.save(toEntity(employee)));
    }


    @GetMapping("all")
    public List<EmployeeResponse> findAllEmployee() {
        return repo.findAll().
                stream()
                .map( i -> new EmployeeResponse(
                        i.getId(),
                        i.getFirstName(),
                        i.getLastName(),
                        i.getEmail(),
                        i.getSalary()
                )).collect(Collectors.toList());

    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id){
        if (repo.existsById(id)){
            repo.deleteById(id);
        }else
            throw new EntityNotFoundException("not found");
    }

    @PutMapping("{id}")
    public EmployeeResponse updateById (@PathVariable int id, @RequestBody EmployeeForm form){

        return toDto(repo.findById(id).map(i-> {
            i.setId(id);
            i.setFirstName(form.firstName);
            i.setLastName(form.lastName);
            i.setSalary(form.salary);
            i.setEmail(form.email);
            return  i;
        }).orElseThrow(()-> new EntityNotFoundException("not found")));
    }


    private EmployeeResponse toDto (Employee employee){
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getSalary()
        );
    }


    private Employee toEntity(EmployeeForm form){
        return new Employee(form.firstName, form.lastName, form.email, form.salary);
    }




}
