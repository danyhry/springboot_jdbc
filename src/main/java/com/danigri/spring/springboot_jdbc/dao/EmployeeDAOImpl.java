package com.danigri.spring.springboot_jdbc.dao;

import com.danigri.spring.springboot_jdbc.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM employees",
                new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public void saveEmployee(Employee employee) {
        jdbcTemplate.update(
                "INSERT INTO employees (name, surname, department, salary) VALUES(?,?,?,?)",
                employee.getName(), employee.getSurname(), employee.getDepartment(), employee.getSalary());
    }

    @Override
    public void updateEmployee(Employee employee) {
        jdbcTemplate.update(
                "UPDATE employees SET name = ?, surname = ?, department = ?, salary = ? where id=?",
                employee.getName(), employee.getSurname(), employee.getDepartment(),
                employee.getSalary(), employee.getId());
    }

    @Override
    public Employee getEmployee(int id) {
        return jdbcTemplate.query("SELECT * FROM employees WHERE id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteEmployee(int id) {
        jdbcTemplate.update("DELETE FROM employees WHERE id = ?", id);
    }
}
