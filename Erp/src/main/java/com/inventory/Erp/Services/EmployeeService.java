package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.EmployeeRepository;
import com.inventory.Erp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getEmployeeLists() {
        return employeeRepository.findAll();
    }
    private Employee findEmployeeById(long id) {
    return employeeRepository.findEmployeeByEmployeeId(id).orElseThrow(()-> new RuntimeException("Record not found"));
    }
    public Employee createNewEmployee(Employee employee) {
        Optional<Employee> checkExistEmployeeByEmail = employeeRepository.findEmployeeByEmail(employee.getEmail());
        Optional<Employee> checkExistEmployeeByContact = employeeRepository.findEmployeeByContact(employee.getContact());

        if(checkExistEmployeeByEmail.isPresent() || checkExistEmployeeByContact.isPresent()) {
            throw new RuntimeException("Record used by another Employee!");
        }
        employee.setEmail(employee.getEmail());
        employee.setContact(employee.getContact());
        employee.setStatus(true);
        employee.setGender(employee.getGender());
        employee.setFirstName(employee.getFirstName());
        employee.setMiddleName(employee.getMiddleName());
        employee.setSurname(employee.getSurname());
        employee.setContractType(employee.getContractType());
        employee.setProfessional(employee.getProfessional());
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeid(long id) {
        Optional<Employee> findEmployeeById = employeeRepository.findById(id);
        if (!findEmployeeById.isPresent()){
            throw new RuntimeException("Employeee records not found!");
        }
        return findEmployeeById.get();
    }
    public Employee updateEmployeeRecords(long id, Employee updateDetails) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setEmployeeId(updateDetails.getEmployeeId());
            employee.setStatus(updateDetails.isStatus());
            employee.setSurname(updateDetails.getSurname());
            employee.setContact(updateDetails.getContact());
            employee.setEmail(updateDetails.getEmail());
            employee.setProfessional(updateDetails.getProfessional());
            employee.setContractType(updateDetails.getContractType());
            employee.setEmploymentDate(updateDetails.getEmploymentDate());
            employee.setMiddleName(updateDetails.getMiddleName());
            employee.setFirstName(updateDetails.getFirstName());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Record not found"));
    }
    public Employee deleteEmployeesRecord(long id){
        Employee deleteEmployee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Record not found"));
         deleteEmployee.setStatus(false);
         return employeeRepository.save(deleteEmployee);
    }
}