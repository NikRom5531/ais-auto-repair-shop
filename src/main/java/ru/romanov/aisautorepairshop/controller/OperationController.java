package ru.romanov.aisautorepairshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.romanov.aisautorepairshop.model.dto.OperationDto;
import ru.romanov.aisautorepairshop.model.entity.Operation;

import java.util.List;

public interface OperationController {
    ResponseEntity<Operation> createOperation(@RequestBody OperationDto request);

    ResponseEntity<Operation> getOperationByUid(@RequestBody OperationDto request);

    ResponseEntity<List<Operation>> getAllOperations();

    ResponseEntity<List<Operation>> getAllOperationsByOrderUid(@RequestBody OperationDto request);

    ResponseEntity<List<Operation>> getAllOperationsByEmployeeUid(@RequestBody OperationDto request);

    ResponseEntity<Operation> finishedOperation(@RequestBody OperationDto request);

    ResponseEntity<Void> deleteOperation(@RequestBody OperationDto request);
}
