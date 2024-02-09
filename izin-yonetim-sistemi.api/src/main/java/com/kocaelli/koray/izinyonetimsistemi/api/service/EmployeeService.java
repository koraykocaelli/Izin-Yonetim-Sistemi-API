package com.kocaelli.koray.izinyonetimsistemi.api.service;

import com.kocaelli.koray.izinyonetimsistemi.api.dto.EmployeeDto;
import com.kocaelli.koray.izinyonetimsistemi.api.entity.Employee;
import com.kocaelli.koray.izinyonetimsistemi.api.util.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employee);

    List<EmployeeDto> getEmployees();
    EmployeeDto getEmployee(Long id);

    EmployeeDto updateEmployee(Long id,EmployeeDto employee);

    Boolean deleteEmployee(Long id);

    Page<Employee> pagination(int currentPage, int pageSize);

    Page<Employee> pagination(Pageable pageable);

    Slice<Employee> slice(Pageable pageable);

    CustomPage<EmployeeDto> customPagination (Pageable pageable);

}

