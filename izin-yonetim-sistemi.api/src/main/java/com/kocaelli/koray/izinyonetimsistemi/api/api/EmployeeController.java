package com.kocaelli.koray.izinyonetimsistemi.api.api;

import com.kocaelli.koray.izinyonetimsistemi.api.dto.EmployeeDto;
import com.kocaelli.koray.izinyonetimsistemi.api.entity.Employee;
import com.kocaelli.koray.izinyonetimsistemi.api.service.EmployeeService;
import com.kocaelli.koray.izinyonetimsistemi.api.util.CustomPage;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employee){
        EmployeeDto resultEmployeemployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(resultEmployeemployee);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<EmployeeDto> employees = employeeService.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity <EmployeeDto> getEmployee(@PathVariable("id") Long id){

        EmployeeDto employee = employeeService.getEmployee(id);

        return ResponseEntity.ok(employee);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto employee){
        EmployeeDto resultEmployee = employeeService.updateEmployee(id,employee);
        return ResponseEntity.ok(resultEmployee);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Employee>> pagination(@RequestParam int currentPage,@RequestParam  int pageSize){
        return ResponseEntity.ok(employeeService.pagination(currentPage,pageSize));
    }

    @GetMapping("/pagination/v1")
    public ResponseEntity<Page<Employee>> pagination(Pageable pageable){
        return ResponseEntity.ok(employeeService.pagination(pageable));
    }


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("id") Long id){
        Boolean status = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(status);
    }


}
