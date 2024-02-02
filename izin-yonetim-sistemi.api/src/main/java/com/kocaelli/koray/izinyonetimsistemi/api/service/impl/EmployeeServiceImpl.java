package com.kocaelli.koray.izinyonetimsistemi.api.service.impl;

import com.kocaelli.koray.izinyonetimsistemi.api.entity.Employee;
import com.kocaelli.koray.izinyonetimsistemi.api.repository.EmployeeRepository;
import com.kocaelli.koray.izinyonetimsistemi.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setCreatedDate(new Date());
        employee.setCreatedBy("admin");
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()){
            return employee.get();
        }
        return null;
    }


    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> resultEmployee = employeeRepository.findById(id);

        if (resultEmployee.isPresent()){
            resultEmployee.get().setFirstName(employee.getFirstName());
            resultEmployee.get().setLastName(employee.getLastName());
            resultEmployee.get().setDepartment(employee.getDepartment());
            resultEmployee.get().setEmail(employee.getEmail());
            resultEmployee.get().setUpdatedBy("Admin");

            resultEmployee.get().setDayOff(employee.getUsedDayOff());


            return employeeRepository.save(resultEmployee.get());
        }
        return null;
    }


}
