package com.ot.employeeCrud.dao;

import com.ot.employeeCrud.dto.Employee;
import com.ot.employeeCrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findByEmpId(String empId) {
        return employeeRepository.findByEmpId(empId);
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
