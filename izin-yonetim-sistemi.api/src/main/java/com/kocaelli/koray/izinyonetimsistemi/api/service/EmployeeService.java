package com.kocaelli.koray.izinyonetimsistemi.api.service;

import com.kocaelli.koray.izinyonetimsistemi.api.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getEmployees();
    Employee getEmployee(Long id);

    Employee updateEmployee(Long id,Employee employee);




}

