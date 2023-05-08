package com.example.employeeexample.service;

import com.example.employeeexample.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee entity) throws Exception;

    Employee update(Employee entity) throws Exception;

    void delete(int id);
}
