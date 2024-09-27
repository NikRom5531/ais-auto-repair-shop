package ru.romanov.aisautorepairshop.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.exception.EntityNotFoundException;
import ru.romanov.aisautorepairshop.model.entity.Operation;
import ru.romanov.aisautorepairshop.repository.OperationRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationCacheService {
    private final OperationRepository operationRepository;

    @Cacheable(value = "operations", key = "#uid")
    public Operation getOperationById(UUID uid) {
        return operationRepository.findById(uid)
                .orElseThrow(() -> new EntityNotFoundException("Operation by UID = " + uid + " not found"));
    }

    @Cacheable("operations")
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Cacheable(value = "operations", key = "#uid")
    public List<Operation> getAllOperationsByOrderId(UUID uid) {
        return operationRepository.findAllByOrderUid(uid);
    }

    @Cacheable(value = "operations", key = "#uid")
    public List<Operation> getAllOperationsByEmployeeId(UUID uid) {
        return operationRepository.findAllByEmployeeUid(uid);
    }
}
