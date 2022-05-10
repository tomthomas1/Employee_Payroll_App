package com.example.EmployeePayroll.repository;

import com.example.EmployeePayroll.model.EmployeePayrollData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeePayrollRepository extends JpaRepository<EmployeePayrollData,Integer> {
    @Query(value = "SELECT * FROM employee_payroll_data e, employee_department ed WHERE e.employee_id = ed.id AND ed.departments = :dept", nativeQuery = true)
    public List<EmployeePayrollData> findEmployeeByDepartment(@Param("dept") String department);
}


      //  "select * from employee_payroll_data, employee_department where employee_id = id and department = :department",nativeQuery = true)