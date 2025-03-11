package com.inventory.Erp.Controller;

import com.inventory.Erp.ExeceptionsHandler.ResourceNotFoundException;
import com.inventory.Erp.Repository.EmployeeRepository;
import com.inventory.Erp.Services.EmployeeService;
import com.inventory.Erp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("*")
public class EmployeeController {
    @Autowired private final EmployeeService employeeService;
    @Autowired private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }
    @RequestMapping(value = "/getEmployeeList",method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> fetchEmployee = employeeService.getEmployeeLists();
        return new ResponseEntity<>(fetchEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "/getEmployeeById/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") long employeeId) {
        try {
            Employee employee = employeeService.findEmployeeid(employeeId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
           } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/createNewEmployee", method = RequestMethod.POST)
    public ResponseEntity<?> createNewEmployee(@RequestBody Employee createNewRecord) {
        try {
             Employee createEmployee = employeeService.createNewEmployee(createNewRecord);
             return new ResponseEntity<>(createEmployee, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/updateEmployee/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee updateEmployee) {
        Employee updatedRecords = employeeService.updateEmployeeRecords(employeeId, updateEmployee);
        return new ResponseEntity<>(updatedRecords, HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteRecord/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRecord(@PathVariable("id") long id){
        Optional<Employee> findRecordToDelete = employeeRepository.findById(id);
        if(findRecordToDelete.isEmpty()) throw new ResourceNotFoundException("record not found");
        else employeeService.deleteEmployeesRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
