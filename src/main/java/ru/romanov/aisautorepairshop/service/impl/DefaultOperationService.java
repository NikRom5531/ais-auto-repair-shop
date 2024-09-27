package ru.romanov.aisautorepairshop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.model.dto.OperationDto;
import ru.romanov.aisautorepairshop.model.entity.Operation;
import ru.romanov.aisautorepairshop.model.enums.OrderStatusEnum;
import ru.romanov.aisautorepairshop.repository.OperationRepository;
import ru.romanov.aisautorepairshop.service.EmployeeService;
import ru.romanov.aisautorepairshop.service.OperationService;
import ru.romanov.aisautorepairshop.service.OrderService;
import ru.romanov.aisautorepairshop.service.WarehouseService;
import ru.romanov.aisautorepairshop.service.cache.OperationCacheService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DefaultOperationService implements OperationService {
    private final OperationRepository operationRepository;

    private final OperationCacheService operationCacheService;

    private final WarehouseService warehouseService;
    private final OrderService orderService;
    private final EmployeeService employeeService;

    @CacheEvict(value = "operations", allEntries = true)
    @Override
    public Operation createOperation(OperationDto operationDto) {
        orderService.changeOrderStatus(operationDto.getOrder_uid(), OrderStatusEnum.IN_PROGRESS);
        Operation newOperation = operationRepository.save(
                Operation.builder()
                        .description(operationDto.getDescription())
                        .started(LocalDateTime.now())
                        .order(orderService.getOrderById(operationDto.getOrder_uid()))
                        .employee(employeeService.getEmployeeById(operationDto.getEmployee_uid()))
                        .build());

        warehouseService.takeRequirementItems(operationDto.getRequirementDtoList(), newOperation);

        return newOperation;
    }

    @Override
    public Operation getOperationById(UUID uid) {
        return operationCacheService.getOperationById(uid);
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationCacheService.getAllOperations();
    }

    @Override
    public List<Operation> getAllOperationsByOrderId(UUID orderUid) {
        return operationCacheService.getAllOperationsByOrderId(orderUid);
    }

    @Override
    public List<Operation> getAllOperationsByEmployeeId(UUID employeeUid) {
        return operationCacheService.getAllOperationsByEmployeeId(employeeUid);
    }

    @CacheEvict(value = "operations", key = "#uid", allEntries = true)
    @Override
    public Operation finishedOperation(UUID uid) {
        Operation operation = getOperationById(uid);
        operation.setFinished(LocalDateTime.now());
        operationRepository.save(operation);
        checkFinishedAllOperationByOrder(operation);
        return operation;
    }

    private void checkFinishedAllOperationByOrder(Operation operation) {
        List<Operation> operations = operationRepository.findAllByOrderUid(operation.getOrder().getUid());
        boolean allOperationsFinished = true;

        for (Operation op : operations) {
            if (op.getFinished() == null || op.getFinished().isAfter(LocalDateTime.now())) {
                allOperationsFinished = false;
            }
        }

        if (allOperationsFinished) {
            orderService.changeOrderStatus(operation.getOrder().getUid(), OrderStatusEnum.COMPLETED);
        }
    }


    @CacheEvict(value = "operations", key = "#uid", allEntries = true)
    @Override
    public void delete(UUID uid) {
        operationRepository.deleteById(uid);
    }
}
