package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.exception.ApplicationException;
import com.example.EmployeeManagementSystem.exception.EmployeeNotFoundException;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployee(String aadharNumber) throws EmployeeNotFoundException {
        return employeeRepository.findById(aadharNumber)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with %s not present", aadharNumber)));
    }


    public String addEmployee(@NotNull EmployeeDTO employeeDTO) throws ApplicationException {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setAadharNumber(employeeDTO.getAadharNumber());
        employee.setDept(employeeDTO.getDept());
        employee.setCity(employeeDTO.getCity());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        Period age = Period.between(employeeDTO.getDateOfBirth(), LocalDate.now());
        employee.setAge(age.getYears());
        employeeRepository.save(employee);
        return employee.getAadharNumber();
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public void updateEmployeeDepartment(String aadharNumber, String dept) throws EmployeeNotFoundException {
        Employee employee = getEmployee(aadharNumber);
        employee.setDept(dept);
        employeeRepository.save(employee);
    }

    public void deleteUser(String aadharNumber) {
        employeeRepository.deleteById(aadharNumber);
    }
}
