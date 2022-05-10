package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeePayrollDTO;
import com.example.EmployeePayroll.dto.ResponseDTO;
import com.example.EmployeePayroll.exception.EmployeeNotFound;
import com.example.EmployeePayroll.model.EmployeePayrollData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IEmployeePayrollService {

    List<EmployeePayrollData> getEmployeePayrollData();

    ResponseEntity<ResponseDTO> updateEmployeePayrollData(int empId,EmployeePayrollDTO employeePayrollDTO) throws EmployeeNotFound;

    ResponseEntity<ResponseDTO> deleteEmployeePayrollData(int empId) throws EmployeeNotFound;

    ResponseEntity<ResponseDTO> createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO);

    ResponseEntity<ResponseDTO> getEmployeePayrollDataById(Optional<Integer> empId) throws EmployeeNotFound;

    ResponseEntity<ResponseDTO> findEmployeeByDept(String dept) throws EmployeeNotFound;
}
