package ru.romanov.aisautorepairshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.exceptions.EmployeeNotFoundException;
import ru.romanov.aisautorepairshop.model.dto.EmployeeDto;
import ru.romanov.aisautorepairshop.model.entity.Employee;
import ru.romanov.aisautorepairshop.repository.EmployeeRepository;
import ru.romanov.aisautorepairshop.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeService implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        return employeeRepository.save(
                Employee.builder()
                        .firstName(employeeDto.getFirstName())
                        .lastName(employeeDto.getLastName())
                        .position(employeeDto.getPosition())
                        .build());
    }

    @Override
    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee assignPositionEmployee(UUID uid, String position) {
        Employee employee = getEmployeeById(uid);
        employee.setPosition(position);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }
}
