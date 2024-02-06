package com.kocaelli.koray.izinyonetimsistemi.api.api;

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
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee resultEmployeemployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(resultEmployeemployee);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employees = employeeService.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity <Employee> getEmployee(@PathVariable("id") Long id){

        Employee employee = employeeService.getEmployee(id);

        return ResponseEntity.ok(employee);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> UpdateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee resultEmployee = employeeService.updateEmployee(id,employee);
        return ResponseEntity.ok(resultEmployee);
    }




    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("id") Long id){
        Boolean status = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(status);
    }





}
