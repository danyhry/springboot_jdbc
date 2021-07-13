package com.danigri.spring.springboot_jdbc.service;


import com.danigri.spring.springboot_jdbc.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    Employee getEmployee(int id);

    void deleteEmployee(int id);
}
