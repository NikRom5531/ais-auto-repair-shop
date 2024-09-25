package ru.romanov.aisautorepairshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.romanov.aisautorepairshop.model.entity.Operation;
import ru.romanov.aisautorepairshop.web.request.OperationRequest;
import ru.romanov.aisautorepairshop.web.request.UidRequest;

import java.util.List;

public interface OperationController {
    ResponseEntity<Operation> createOperation(@RequestBody OperationRequest request);

    ResponseEntity<Operation> getOperationById(@RequestBody UidRequest request);

    List<Operation> getAllOperations();

    List<Operation> getAllOperationsByOrderId(@RequestBody UidRequest request);

    List<Operation> getAllOperationsByEmployeeId(@RequestBody UidRequest request);

    ResponseEntity<Operation> finishedOperation(@RequestBody UidRequest request);

    ResponseEntity<Void> deleteOperation(@RequestBody UidRequest request);
}
