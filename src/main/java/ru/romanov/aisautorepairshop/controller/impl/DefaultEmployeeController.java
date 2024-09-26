package ru.romanov.aisautorepairshop.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Employee> getEmployeeById(@RequestBody EmployeeDto request) {
        return ResponseEntity.ok(employeeService.getEmployeeById(request.getUid()));
    }

    @Override
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Override
    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeDto request) {
        return ResponseEntity.ok(employeeService.assignPositionEmployee(request.getUid(), request.getPosition()));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(@RequestBody EmployeeDto request) {
        employeeService.deleteEmployee(request.getUid());
        return ResponseEntity.noContent().build();
    }
}
