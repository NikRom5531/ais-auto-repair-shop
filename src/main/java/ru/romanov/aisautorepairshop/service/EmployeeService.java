package ru.romanov.aisautorepairshop.service;

import ru.romanov.aisautorepairshop.model.entity.Employee;
import ru.romanov.aisautorepairshop.web.payload.EmployeePayload;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Employee createEmployee(EmployeePayload payload);
    Employee assignPositionEmployee(UUID uid, String position);
    void deleteEmployee(UUID id);
    Employee getEmployeeById(UUID id);
    List<Employee> getAllEmployees();
}
