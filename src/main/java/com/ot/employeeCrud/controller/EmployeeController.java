package com.ot.employeeCrud.controller;

import com.ot.employeeCrud.dto.Employee;
import com.ot.employeeCrud.dto.ResponseStructure;
import com.ot.employeeCrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseStructure<Employee>> save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping(value = "/findBy/{id}")
    public ResponseEntity<ResponseStructure<Employee>> findById(@PathVariable long id) {
        return employeeService.findById(id);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseStructure<Employee>> delete(@RequestParam long id) {
        return employeeService.delete(id);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseStructure<List<Employee>>> findAll() {
        return employeeService.findAll();
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }
}