package com.example.employeeexample.service.impl;

import com.example.employeeexample.entity.Employee;
import com.example.employeeexample.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Employee findById(int id) {
        return employees.stream().filter(x -> x.getId() == id).findAny()
                .orElse(null);
    }

    @Override
    public Employee save(Employee entity) throws Exception {
        // TODO: Validate
        Employee currentEntity = employees.stream().filter(x -> x.getEmail().equals(entity.getEmail())).findAny()
                .orElse(null);
        if (currentEntity == null) {
            int id = 1;
            if (employees.size() > 0) {
                Employee maxIdEntity = employees.stream().max(Comparator.comparing(Employee::getId)).get();
                id = maxIdEntity.getId() + 1;
            }
            entity.setId(id);
            employees.add(entity);
        } else {
            throw new Exception("Email đã tồn tại!");
        }
        return entity;
    }

    @Override
    public Employee update(Employee entity) throws Exception {
        Employee currentEntity = employees.stream().filter(x -> x.getId() == entity.getId()).findAny()
                .orElse(null);
        // Validate email, then update
        // currentEntity.
        if (currentEntity != null) {
            Employee emailEntity = employees.stream().filter(x -> x.getEmail().equals(entity.getEmail())).findAny()
                    .orElse(null);
            if (emailEntity == null || emailEntity.getId() == currentEntity.getId()) {
                currentEntity.setEmail(entity.getEmail());
            } else {
                throw new Exception("Email đã tồn tại!");
            }
            currentEntity.setName(entity.getName());
            employees.add(entity);
        } else {
            throw new Exception("Nhân viên có id: " + entity.getId() + " không tồn tại");
        }
        return entity;
    }

    @Override
    public void delete(int id) {

    }
}
