package com.ot.employeeCrud.service;

import com.ot.employeeCrud.dao.EmployeeDao;
import com.ot.employeeCrud.dto.Employee;
import com.ot.employeeCrud.dto.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public ResponseEntity<ResponseStructure<Employee>> save(Employee employee) {
        ResponseStructure<Employee> responseStructure = new ResponseStructure<>();

        Employee employee1 = employeeDao.findByEmpId(employee.getEmpId());

        if (Objects.isNull(employee1)) {
            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("Employee created successfully");
            responseStructure.setData(employee);
            employeeDao.save(employee);
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        } else {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Employee already exists");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<ResponseStructure<Employee>> update(Employee employee) {
        ResponseStructure<Employee> responseStructure = new ResponseStructure<>();
        Employee employee1 = employeeDao.findById(employee.getId());
        if (Objects.isNull(employee1)) {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Employee does not exist to update " + employee.getEmpId());
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Employee updated successfully");
            responseStructure.setData(employee);
            employeeDao.update(employee);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Employee>> findById(long id) {
        ResponseStructure<Employee> responseStructure = new ResponseStructure<>();
        Employee employee = employeeDao.findById(id);

        if (Objects.isNull(employee)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Employee id does not exist" + id);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Employee found by id " + id);
            responseStructure.setData(employee);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<List<Employee>>> findAll() {
        ResponseStructure<List<Employee>> responseStructure = new ResponseStructure<>();
        List<Employee> employeeList = employeeDao.findAll();
        if (Objects.isNull(employeeList)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Employees does not exist ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Found all employees ");
            responseStructure.setData(employeeList);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Employee>> delete(long id) {
        ResponseStructure<Employee> responseStructure = new ResponseStructure<>();
        Employee employee = employeeDao.findById(id);
        if (Objects.isNull(employee)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("employee does not exist " + id);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            employeeDao.delete(employee);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Employee deleted successfully " + id);
            responseStructure.setData(employee);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }
    }
}