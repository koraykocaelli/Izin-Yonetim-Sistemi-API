package com.kocaelli.koray.izinyonetimsistemi.api.repository;

import com.kocaelli.koray.izinyonetimsistemi.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
