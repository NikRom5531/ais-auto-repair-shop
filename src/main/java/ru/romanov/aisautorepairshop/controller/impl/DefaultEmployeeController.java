package ru.romanov.aisautorepairshop.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanov.aisautorepairshop.controller.EmployeeController;
import ru.romanov.aisautorepairshop.model.dto.EmployeeDto;
import ru.romanov.aisautorepairshop.model.entity.Employee;
import ru.romanov.aisautorepairshop.service.EmployeeService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class DefaultEmployeeController implements EmployeeController {
    private EmployeeService employeeService;

    @Override
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto request) {
        return ResponseEntity.status(201).body(employeeService.createEmployee(request));
    }

    @Override
    @GetMapping("/employee")
    public ResponseEntity<Employee> getEmployeeByUid(@RequestBody EmployeeDto request) {
        return ResponseEntity.ok(employeeService.getEmployeeById(request.getUid()));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(employees.isEmpty() ? 204 : 200).body(employees);
    }

    @Override
    @PutMapping("/employee")
    public ResponseEntity<Employee> assignPositionEmployee(@RequestBody EmployeeDto request) {
        return ResponseEntity.ok(employeeService.assignPositionEmployee(request.getUid(), request.getPosition()));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(@RequestBody EmployeeDto request) {
        employeeService.deleteEmployee(request.getUid());
        return ResponseEntity.noContent().build();
    }
}
