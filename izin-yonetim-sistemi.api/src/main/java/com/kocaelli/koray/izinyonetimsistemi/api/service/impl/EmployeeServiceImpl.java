package com.kocaelli.koray.izinyonetimsistemi.api.service.impl;

import com.kocaelli.koray.izinyonetimsistemi.api.advice.EmployeeNotFound;
import com.kocaelli.koray.izinyonetimsistemi.api.dto.EmployeeDto;
import com.kocaelli.koray.izinyonetimsistemi.api.entity.Employee;
import com.kocaelli.koray.izinyonetimsistemi.api.repository.EmployeeRepository;
import com.kocaelli.koray.izinyonetimsistemi.api.service.EmployeeService;
import com.kocaelli.koray.izinyonetimsistemi.api.util.CustomPage;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        throw new RuntimeException("Kullanıcı Bulunamadı");
    }


    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Optional<Employee> resultEmployee = employeeRepository.findById(id);

        if (resultEmployee.isPresent()) {
            Employee existingEmployee = resultEmployee.get();
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setLastName(employeeDto.getLastName());
            existingEmployee.setDepartment(employeeDto.getDepartment());
            existingEmployee.setEmail(employeeDto.getEmail());
            existingEmployee.setUpdatedBy("Admin");

            // Güncellenmiş kullanılan izin günleri
            int updatedUsedDayOff = employeeDto.getUsedDayOff();

            // Önceki kullanılan izin günlerini al
            int previousUsedDayOff = existingEmployee.getUsedDayOff();

            // Toplam kullanılan izin günlerini hesapla
            int totalUsedDayOff = updatedUsedDayOff + previousUsedDayOff;

            // Toplam kullanılan izin günleri maksimum 15 olmalı
            if (totalUsedDayOff > 15) {
                throw new IllegalArgumentException("Toplam kullanılan izin günleri maksimum 15 olmalıdır.");
            }

            // Kalan izin günlerini hesapla
            int remainingLeaveDays = existingEmployee.getDayOff() - updatedUsedDayOff;

            // Kalan izin günleri minimum 0 olmalı
            if (remainingLeaveDays < 0) {
                throw new IllegalArgumentException("Kalan izin günleri minimum 0 olmalıdır.");
            }

            // Güncellenmiş kullanılan izin günlerini ve kalan izin günlerini ayarla
            existingEmployee.setUsedDayOff(totalUsedDayOff);
            existingEmployee.setDayOff(remainingLeaveDays);

            return modelMapper.map(employeeRepository.save(existingEmployee), EmployeeDto.class);
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

    @Override
    public Page<Employee> pagination(int currentPage, int pageSize) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);


        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> pagination(Pageable pageable) {

        return employeeRepository.findAll(pageable);
    }

    @Override
    public Slice<Employee> slice(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public CustomPage<EmployeeDto> customPagination(Pageable pageable) {
        Page<Employee> data = employeeRepository.findAll(pageable);
        EmployeeDto[] dtos = modelMapper.map(data.getContent(), EmployeeDto[].class);
        return new CustomPage<EmployeeDto>(data, Arrays.asList(dtos));
    }
}
