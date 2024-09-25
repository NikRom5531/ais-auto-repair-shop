package ru.romanov.aisautorepairshop.service;

import ru.romanov.aisautorepairshop.model.entity.Operation;
import ru.romanov.aisautorepairshop.web.payload.OperationPayload;

import java.util.List;
import java.util.UUID;

public interface OperationService {
    Operation createOperation(OperationPayload payload);

    Operation getOperationById(UUID uid);

    List<Operation> getAllOperations();

    List<Operation> getAllOperationsByOrderId(UUID orderUid);

    List<Operation> getAllOperationsByEmployeeId(UUID employeeUid);

    Operation finishedOperation(UUID uid);

    void delete(UUID uid);
}
