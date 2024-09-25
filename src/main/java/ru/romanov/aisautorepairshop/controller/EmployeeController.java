package ru.romanov.aisautorepairshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.romanov.aisautorepairshop.model.entity.Employee;
import ru.romanov.aisautorepairshop.web.request.EmployeeRequest;
import ru.romanov.aisautorepairshop.web.request.UidRequest;
import ru.romanov.aisautorepairshop.web.request.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeController {
    ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest request);
    ResponseEntity<Employee> getEmployeeById(@RequestBody UidRequest request);
    List<Employee> getAllEmployees();
    ResponseEntity<Employee> updateEmployee(@RequestBody UpdateEmployeeRequest request);
    ResponseEntity<Void> deleteEmployee(@RequestBody UidRequest request);
}
