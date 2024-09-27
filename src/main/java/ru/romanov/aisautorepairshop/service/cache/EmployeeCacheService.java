package ru.romanov.aisautorepairshop.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.exception.EntityNotFoundException;
import ru.romanov.aisautorepairshop.model.entity.Employee;
import ru.romanov.aisautorepairshop.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeCacheService {
    private final EmployeeRepository employeeRepository;

    @Cacheable(value = "employees", key = "#uid")
    public Employee getEmployeeById(UUID uid) {
        return employeeRepository.findById(uid)
                .orElseThrow(() -> new EntityNotFoundException("Employee with UID = " + uid + " not found"));
    }

    @Cacheable("employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

