package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeePayrollDTO;
import com.example.EmployeePayroll.dto.ResponseDTO;
import com.example.EmployeePayroll.exception.EmployeeNotFound;
import com.example.EmployeePayroll.model.EmployeePayrollData;
import com.example.EmployeePayroll.repository.EmployeePayrollRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeePayrollServiceImpl implements IEmployeePayrollService {

    @Autowired
    EmployeePayrollRepository employeePayrollRepository;

    // This will insert a new employee into the database.
    // It will go through validation and will throw an error if the validation doesn't pass.
    @Override
    public ResponseEntity<ResponseDTO> createEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO) {
        log.info("Saving a new employee record in the db");
        EmployeePayrollData empData = employeePayrollRepository.save(new EmployeePayrollData(employeePayrollDTO));
        ResponseDTO responseDto = new ResponseDTO("New Employee record has been stored successfully", empData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    //This will list all the employee in the database
    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        log.info("Retrieving employee information from the db");
        return employeePayrollRepository.findAll();
    }

    // This will return the employee we search for by id. If it doesn't find the record of the given id it will
    // throw a custom exception. Which is handled by our custom exception handler.
    @Override
    public ResponseEntity<ResponseDTO> getEmployeePayrollDataById(Optional<Integer> empId) throws EmployeeNotFound {
        log.info("Retrieving the employee data by employee ID");
        ResponseDTO responseDto;
        if (empId.isEmpty()) {
            List<EmployeePayrollData> empData = (List<EmployeePayrollData>) employeePayrollRepository.findAll();
            responseDto = new ResponseDTO("Returning all the records stored in the db ", empData);
            ResponseEntity<ResponseDTO> responseThing = new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
            return responseThing;
        }

        Optional<EmployeePayrollData> employee = employeePayrollRepository.findById((empId.get()));
        EmployeePayrollData empData = employee.orElse(null);

        if (employee.isPresent()) {
            responseDto = new ResponseDTO("Found the employee record ", empData);
            return new ResponseEntity<ResponseDTO>(new ResponseDTO("Found the employee record ", empData),
                    HttpStatus.OK);
        } else {
            throw new EmployeeNotFound("ERROR: Could not find the employee record! ");
        }
    }


    // This will update an existing employee in the database. If it doesn't find the
    // record of the given id it will
    // throw a custom exception. Which is handled by our custom exception handler.
    @Override
    public ResponseEntity<ResponseDTO>  updateEmployeePayrollData(int empId, @Valid EmployeePayrollDTO employeePayrollDTO) throws EmployeeNotFound {
        log.info("Updating the employee data");
        Optional<EmployeePayrollData> emp = employeePayrollRepository.findById(empId);
        if (emp.isEmpty()) {
            throw new EmployeeNotFound(" ERROR: Employee record not found!");
        }
        EmployeePayrollData empData = employeePayrollRepository.save(new EmployeePayrollData(empId, employeePayrollDTO));
        ResponseDTO responseDto = new ResponseDTO(" Employee record has been updated", empData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // This will delete an employee record from the database. If it doesn't find the
    // record of the given id it will
    // throw a custom exception. Which is handled by our custom exception handler.
    @Override
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(int empId) throws EmployeeNotFound {
        log.info("Deleting an employee record");
        if (employeePayrollRepository.findById(empId).isPresent()) {
            employeePayrollRepository.deleteById(empId);
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(" Employee record deleted!", null), HttpStatus.OK);
        } else
            throw new EmployeeNotFound("ERROR: No such employee record found!");
    }
}
