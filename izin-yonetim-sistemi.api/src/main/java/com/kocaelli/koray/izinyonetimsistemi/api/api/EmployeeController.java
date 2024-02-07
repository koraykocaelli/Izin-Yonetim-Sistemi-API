package com.kocaelli.koray.izinyonetimsistemi.api.api;

import com.kocaelli.koray.izinyonetimsistemi.api.dto.EmployeeDto;
import com.kocaelli.koray.izinyonetimsistemi.api.entity.Employee;
import com.kocaelli.koray.izinyonetimsistemi.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDto> UpdateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employee){
        EmployeeDto resultEmployee = employeeService.updateEmployee(id,employee);
        return ResponseEntity.ok(resultEmployee);
    }




    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("id") Long id){
        Boolean status = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(status);
    }





}
