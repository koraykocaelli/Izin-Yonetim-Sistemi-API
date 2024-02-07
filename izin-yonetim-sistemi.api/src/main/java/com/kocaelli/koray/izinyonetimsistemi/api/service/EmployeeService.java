package com.kocaelli.koray.izinyonetimsistemi.api.service;

import com.kocaelli.koray.izinyonetimsistemi.api.dto.EmployeeDto;
import com.kocaelli.koray.izinyonetimsistemi.api.entity.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employee);

    List<EmployeeDto> getEmployees();
    EmployeeDto getEmployee(Long id);

    EmployeeDto updateEmployee(Long id,EmployeeDto employee);

    Boolean deleteEmployee(Long id);


}

