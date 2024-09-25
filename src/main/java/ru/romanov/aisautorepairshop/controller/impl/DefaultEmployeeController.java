package ru.romanov.aisautorepairshop.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.romanov.aisautorepairshop.controller.EmployeeController;
import ru.romanov.aisautorepairshop.model.entity.Employee;
import ru.romanov.aisautorepairshop.service.EmployeeService;
import ru.romanov.aisautorepairshop.web.mapper.EmployeeMapper;
import ru.romanov.aisautorepairshop.web.mapper.UidMapper;
import ru.romanov.aisautorepairshop.web.payload.AssignEmployeePositionPayload;
import ru.romanov.aisautorepairshop.web.request.EmployeeRequest;
import ru.romanov.aisautorepairshop.web.request.UidRequest;
import ru.romanov.aisautorepairshop.web.request.UpdateEmployeeRequest;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class DefaultEmployeeController implements EmployeeController {
    private EmployeeService employeeService;

    @Override
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest request) {
        return ResponseEntity.status(201).body(employeeService.createEmployee(EmployeeMapper.INSTANCE.toPayload(request)));
    }

    @Override
    @GetMapping("/employee")
    public ResponseEntity<Employee> getEmployeeById(@RequestBody UidRequest request) {
        return ResponseEntity.ok(employeeService.getEmployeeById(UidMapper.INSTANCE.toPayload(request).getUid()));
    }

    @Override
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Override
    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody UpdateEmployeeRequest request) {
        AssignEmployeePositionPayload payload = EmployeeMapper.INSTANCE.toPayload(request);
        return ResponseEntity.ok(employeeService.assignPositionEmployee(payload.getUid(), payload.getPosition()));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(@RequestBody UidRequest request) {
        employeeService.deleteEmployee(UidMapper.INSTANCE.toPayload(request).getUid());
        return ResponseEntity.noContent().build();
    }
}
