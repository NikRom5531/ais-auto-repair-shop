package ru.romanov.aisautorepairshop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.exception.OperationNotFoundException;
import ru.romanov.aisautorepairshop.model.dto.OperationDto;
import ru.romanov.aisautorepairshop.model.entity.Operation;
import ru.romanov.aisautorepairshop.model.enums.OrderStatusEnum;
import ru.romanov.aisautorepairshop.repository.InventoryRequirementRepository;
import ru.romanov.aisautorepairshop.repository.OperationRepository;
import ru.romanov.aisautorepairshop.service.EmployeeService;
import ru.romanov.aisautorepairshop.service.OperationService;
import ru.romanov.aisautorepairshop.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DefaultOperationService  { //implements OperationService
    private final OperationRepository operationRepository;
    private final InventoryRequirementRepository requirementRepository;

    private final OrderService orderService;
    private final EmployeeService employeeService;

//    @Override
    public Operation createOperation(OperationDto operationDto) {
        orderService.changeOrderStatus(operationDto.getOrder_uid(), OrderStatusEnum.IN_PROGRESS);
        Operation newOperation = operationRepository.save(Operation.builder()
                .description(operationDto.getDescription())
                .started(LocalDateTime.now())
                .order(orderService.getOrderById(operationDto.getOrder_uid()))
                .employee(employeeService.getEmployeeById(operationDto.getEmployee_uid()))
                .build());



        return operationRepository.save(
                Operation.builder()
                        .description(operationDto.getDescription())
                        .started(LocalDateTime.now())
                        .order(orderService.getOrderById(operationDto.getOrder_uid()))
                        .employee(employeeService.getEmployeeById(operationDto.getEmployee_uid()))
                        .build());
    }

//    @Override
    public Operation getOperationById(UUID uid) {
        return operationRepository.findById(uid)
                .orElseThrow(() -> new OperationNotFoundException("Operation not found"));
    }

//    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

//    @Override
    public List<Operation> getAllOperationsByOrderId(UUID orderUid) {
        return operationRepository.findAllByOrderUid(orderUid);
    }

//    @Override
    public List<Operation> getAllOperationsByEmployeeId(UUID employeeUid) {
        return operationRepository.findAllByEmployeeUid(employeeUid);
    }

//    @Override
    public Operation finishedOperation(UUID uid) {
        Operation operation = getOperationById(uid);
        operation.setFinished(LocalDateTime.now());
        return operationRepository.save(operation);
    }

//    @Override
    public void delete(UUID uid) {
//        Operation operation = getOperationById(uid);
        operationRepository.deleteById(uid);
    }
}
