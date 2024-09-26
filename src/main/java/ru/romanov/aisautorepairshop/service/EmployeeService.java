package ru.romanov.aisautorepairshop.service;

import ru.romanov.aisautorepairshop.model.dto.EmployeeDto;
import ru.romanov.aisautorepairshop.model.entity.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Employee createEmployee(EmployeeDto employeeDto);

    Employee assignPositionEmployee(UUID uid, String position);

    void deleteEmployee(UUID id);

    Employee getEmployeeById(UUID id);

    List<Employee> getAllEmployees();
}
