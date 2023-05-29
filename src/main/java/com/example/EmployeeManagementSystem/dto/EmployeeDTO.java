package com.example.EmployeeManagementSystem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class EmployeeDTO {
    @Id
    @NotNull(message = "Enter Aadhar Number")
    @Pattern(regexp = "[0-9]+", message = "Aadhar field should only contain numbers.")
    @Size(min = 12, max = 12, message = "Aadhar number should be of length 12")
    @Column(name = "aadhar_number")
    private String aadharNumber;

    @NotNull(message = "Enter name.")
    @Column(name = "name")
    private String name;

    @PastOrPresent(message = "DOB should not be future date")
    @Column(name = "DOB")
    private LocalDate dateOfBirth;

    @Column(name = "dept")
    private String dept;

    @Column(name = "city")
    private String city;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "aadharNumber='" + aadharNumber + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dept='" + dept + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
