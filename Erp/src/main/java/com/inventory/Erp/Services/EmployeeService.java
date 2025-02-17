package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.EmployeeRepository;
import com.inventory.Erp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeLists() {
        return employeeRepository.findAll();
    }

    private Employee findEmployeeById(long id) {
    return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public Employee createNewEmployee(Employee employee) {
        Optional<Employee> checkExistEmployeeByEmail = employeeRepository.findEmployeeByEmail(employee.getEmail());
        Optional<Employee> checkContactByContact = employeeRepository.findEmployeeByContact(employee.getContact());
        if(checkExistEmployeeByEmail.isPresent() || checkContactByContact.isPresent())
            throw new RuntimeException("Record used by another Employee!");
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
        if (!findEmployeeById.isPresent()) {
            throw new RuntimeException("No Record ");
        }
        return findEmployeeById.get();
    }

    public Employee updateEmployeeRecords(long id, Employee updateDetails){
        Optional<Employee> findEmployeeById = employeeRepository.findById(id);
        if (!findEmployeeById.isPresent()) {
            throw new RuntimeException("Record not found");
        }
        Employee updateRecords = findEmployeeById.get();
        updateRecords.setFirstName(updateDetails.getFirstName());
        updateRecords.setMiddleName(updateDetails.getMiddleName());
        updateRecords.setSurname(updateDetails.getSurname());
        updateRecords.setEmail(updateDetails.getEmail());
        updateRecords.setContact(updateDetails.getContact());
        updateRecords.setGender(updateDetails.getGender());
        updateRecords.setContractType(updateDetails.getContractType());
        updateRecords.setProfessional(updateDetails.getProfessional());
        updateRecords.setEmploymentDate(updateDetails.getEmploymentDate());
        updateRecords.setStatus(false);
         return  employeeRepository.save(updateRecords);
    }
    public Employee deleteEmployeesRecord(long id){
        Employee deleteEmployee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Record not found"));
         deleteEmployee.setStatus(false);
         return employeeRepository.save(deleteEmployee);
    }
}