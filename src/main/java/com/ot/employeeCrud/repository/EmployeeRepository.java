package com.ot.employeeCrud.repository;

import com.ot.employeeCrud.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    public Employee findByEmpId(String empId);
}
