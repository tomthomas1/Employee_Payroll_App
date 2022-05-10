package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeePayrollDTO;
import com.example.EmployeePayroll.dto.ResponseDTO;
import com.example.EmployeePayroll.exception.EmployeeNotFound;
import com.example.EmployeePayroll.model.EmployeePayrollData;
import com.example.EmployeePayroll.service.IEmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeePayrollService;

    // This will call the service layer to create a new employee record in the database
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
        return  employeePayrollService.createEmployeePayrollData(empPayrollDTO);
    }

    // This call will list all the employee in the database
    @RequestMapping(value = {"/list"})
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeePayrollService.getEmployeePayrollData();
    }

    // This will call the service layer to get an employee we search for by id
    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrolIData(@PathVariable Optional<Integer> empId) throws EmployeeNotFound {
        return employeePayrollService.getEmployeePayrollDataById(empId);
    }

    // This will call the service layer to update an employee record
    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable int empId,@Valid @RequestBody EmployeePayrollDTO empPayrollDTO) throws EmployeeNotFound {
        return employeePayrollService.updateEmployeePayrollData(empId,empPayrollDTO);
    }

    //This will delete an employee record, specified by id, from the database
    @DeleteMapping("/delete/{empId}")
    public  ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) throws EmployeeNotFound {
        return employeePayrollService.deleteEmployeePayrollData(empId);
    }

}
