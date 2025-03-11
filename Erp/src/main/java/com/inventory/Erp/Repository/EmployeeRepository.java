package com.inventory.Erp.Repository;

import com.inventory.Erp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findEmployeeByEmail(String email);
    Optional<Employee> findEmployeeByContact(String contact);
    Optional<Employee> findEmployeeByEmployeeId(long EmployeeId);
    Optional<Employee> findEmployeeByStatus(boolean inactive);
}
