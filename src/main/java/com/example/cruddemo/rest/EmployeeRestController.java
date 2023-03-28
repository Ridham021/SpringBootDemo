package com.example.cruddemo.rest;

import com.example.cruddemo.dao.EmployeeDAO;
import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeRestService;
import com.example.cruddemo.service.EmployeeRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeRestService employeeRestService;

    // inject employeeDAO (use constructor Injection)

    @Autowired
    public EmployeeRestController(EmployeeRestService theEmployeeRestService){
        employeeRestService = theEmployeeRestService;
    }

    // expose "/employees" and return list of employees
    int count=0;
    @GetMapping("/employees")
    public List<Employee> findAll() throws InterruptedException {
        Thread.sleep(2);
        System.out.println("Hited here"+count++);

        return employeeRestService.findAll();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId){


        Employee theEmployee =  employeeRestService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee Id not found "+employeeId);
        }
        else {
            return theEmployee;
        }

    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        employeeRestService.save(theEmployee);
        return theEmployee;
    }

    @DeleteMapping("employee/{employeeId}")
    public String deleteById(@PathVariable int employeeId){
        employeeRestService.deleteById(employeeId);
        return "employee with id : "+employeeId+" is deleted";
    }

    // update existing Employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        employeeRestService.save(theEmployee);

        return theEmployee;
    }



}
