package com.example.EmployeeManagementSystem.controller;


import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.exception.ApplicationException;
import com.example.EmployeeManagementSystem.exception.EmployeeNotFoundException;
import com.example.EmployeeManagementSystem.exception.ResponseHandler;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Validated
public class EmployeeController {

    private final String employeeInsertionSuccess = "INSERT_SUCCESS with aadharNumber: ";
    private final String updateMessage = "UPDATE_SUCCESS";
    private final String deleteMessage = "DELETE_SUCCESS";
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeNotFoundException {
        List<Employee> employeeList = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/employees/{aadharNumber}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String aadharNumber) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployee(aadharNumber);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<Object> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws ApplicationException {
        String aadharNumber = employeeService.addEmployee(employeeDTO);
        String successMessage = employeeInsertionSuccess + aadharNumber;
        return ResponseHandler.generateResponse(successMessage, HttpStatus.CREATED);
    }

    @PutMapping(value = "/employees/{aadharNumber}")
    public ResponseEntity<Object> updateEmployee(@PathVariable String aadharNumber, @RequestBody @NotNull(message = "Department field cannot be null") String dept)
            throws ApplicationException, EmployeeNotFoundException {
        employeeService.updateEmployeeDepartment(aadharNumber, dept);
        return ResponseHandler.generateResponse(updateMessage, HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/{aadharNumber}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String aadharNumber) throws ApplicationException {
        employeeService.deleteUser(aadharNumber);
        return ResponseHandler.generateResponse(deleteMessage, HttpStatus.OK);

    }
}
