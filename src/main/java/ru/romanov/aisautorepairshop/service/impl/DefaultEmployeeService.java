package ru.romanov.aisautorepairshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.model.dto.EmployeeDto;
import ru.romanov.aisautorepairshop.model.entity.Employee;
import ru.romanov.aisautorepairshop.repository.EmployeeRepository;
import ru.romanov.aisautorepairshop.service.EmployeeService;
import ru.romanov.aisautorepairshop.service.cache.EmployeeCacheService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeService implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeCacheService employeeCacheService;

    @CacheEvict(value = "employees", allEntries = true)
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
    public Employee getEmployeeById(UUID uid) {
        return employeeCacheService.getEmployeeById(uid);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeCacheService.getAllEmployees();
    }

    @CacheEvict(value = "employees", key = "#uid", allEntries = true)
    @Override
    public Employee assignPositionEmployee(UUID uid, String position) {
        Employee employee = getEmployeeById(uid);
        employee.setPosition(position);
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = "employees", key = "#uid", allEntries = true)
    @Override
    public void deleteEmployee(UUID uid) {
        employeeRepository.deleteById(uid);
    }
}
