package com.example.employeeexample.controller;

import com.example.employeeexample.entity.Employee;
import com.example.employeeexample.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employee")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getAll(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping("employee")
    public ResponseEntity create(@RequestBody Employee entity) {
        try {
            return ResponseEntity.ok(employeeService.save(entity));
        } catch (Exception ex) {
           return  ResponseEntity.ok(ex.getMessage());
        }
    }

    @PutMapping("employee")
    public ResponseEntity update(@RequestBody Employee entity) {
        try {
            return ResponseEntity.ok(employeeService.update(entity));
        } catch (Exception ex) {
            return  ResponseEntity.ok(ex.getMessage());
        }
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<Void> update(@PathVariable int id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
