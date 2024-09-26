package ru.romanov.aisautorepairshop.service;

import ru.romanov.aisautorepairshop.model.dto.OperationDto;
import ru.romanov.aisautorepairshop.model.entity.Operation;

import java.util.List;
import java.util.UUID;

public interface OperationService {
    Operation createOperation(OperationDto operationDto);

    Operation getOperationById(UUID uid);

    List<Operation> getAllOperations();

    List<Operation> getAllOperationsByOrderId(UUID orderUid);

    List<Operation> getAllOperationsByEmployeeId(UUID employeeUid);

    Operation finishedOperation(UUID uid);

    void delete(UUID uid);
}
