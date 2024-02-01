package com.kocaelli.koray.izinyonetimsistemi.api.service.impl;

import com.kocaelli.koray.izinyonetimsistemi.api.repository.EmployeeRepository;
import com.kocaelli.koray.izinyonetimsistemi.api.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
