package com.kocaelli.koray.izinyonetimsistemi.api.service.impl;

import com.kocaelli.koray.izinyonetimsistemi.api.dto.EmployeeDto;
import com.kocaelli.koray.izinyonetimsistemi.api.entity.Employee;
import com.kocaelli.koray.izinyonetimsistemi.api.repository.EmployeeRepository;
import com.kocaelli.koray.izinyonetimsistemi.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setCreatedDate(new Date());
        employee.setCreatedBy("admin");
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> dtos = employees.stream().map(employee -> modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()){
            return modelMapper.map(employee.get(),EmployeeDto.class);
        }
        return null;
    }


    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employee) {
        Optional<Employee> resultEmployee = employeeRepository.findById(id);

        if (resultEmployee.isPresent()){
            resultEmployee.get().setFirstName(employee.getFirstName());
            resultEmployee.get().setLastName(employee.getLastName());
            resultEmployee.get().setDepartment(employee.getDepartment());
            resultEmployee.get().setEmail(employee.getEmail());
            resultEmployee.get().setUpdatedBy("Admin");

            resultEmployee.get().setDayOff(employee.getUsedDayOff());


            return modelMapper.map(employeeRepository.save(resultEmployee.get()),EmployeeDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
