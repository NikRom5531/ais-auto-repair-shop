package ru.romanov.aisautorepairshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.romanov.aisautorepairshop.model.dto.EmployeeDto;
import ru.romanov.aisautorepairshop.model.entity.Employee;

import java.util.List;

public interface EmployeeController {
    ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto request);

    ResponseEntity<Employee> getEmployeeById(@RequestBody EmployeeDto request);

    ResponseEntity<List<Employee>> getAllEmployees();

    ResponseEntity<Employee> assignPositionEmployee(@RequestBody EmployeeDto request);

    ResponseEntity<Void> deleteEmployee(@RequestBody EmployeeDto request);
}
