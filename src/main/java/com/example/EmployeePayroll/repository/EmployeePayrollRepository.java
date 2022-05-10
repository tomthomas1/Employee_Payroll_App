package com.example.EmployeePayroll.repository;

import com.example.EmployeePayroll.model.EmployeePayrollData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeePayrollRepository extends JpaRepository<EmployeePayrollData,Integer> {
}
