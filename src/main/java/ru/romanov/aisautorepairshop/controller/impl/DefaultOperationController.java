package ru.romanov.aisautorepairshop.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanov.aisautorepairshop.controller.OperationController;
import ru.romanov.aisautorepairshop.model.dto.OperationDto;
import ru.romanov.aisautorepairshop.model.entity.Operation;
import ru.romanov.aisautorepairshop.service.OperationService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/operations")
public class DefaultOperationController implements OperationController {
    private final OperationService operationService;

    @Override
    @PostMapping
    public ResponseEntity<Operation> createOperation(OperationDto request) {
        return ResponseEntity.status(201).body(operationService.createOperation(request));
    }

    @Override
    @GetMapping
    public ResponseEntity<Operation> getOperationByUid(OperationDto request) {
        return ResponseEntity.ok(operationService.getOperationById(request.getUid()));
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Operation>> getAllOperations() {
        List<Operation> operations = operationService.getAllOperations();
        return ResponseEntity.status(operations.isEmpty() ? 204 : 200).body(operations);
    }

    @Override
    @GetMapping("/order")
    public ResponseEntity<List<Operation>> getAllOperationsByOrderUid(OperationDto request) {
        List<Operation> operations = operationService.getAllOperationsByOrderId(request.getOrder_uid());
        return ResponseEntity.status(operations.isEmpty() ? 204 : 200).body(operations);
    }

    @Override
    @GetMapping("/employee")
    public ResponseEntity<List<Operation>> getAllOperationsByEmployeeUid(OperationDto request) {
        List<Operation> operations = operationService.getAllOperationsByEmployeeId(request.getEmployee_uid());
        return ResponseEntity.status(operations.isEmpty() ? 204 : 200).body(operations);
    }

    @Override
    @PatchMapping
    public ResponseEntity<Operation> finishedOperation(OperationDto request) {
        return ResponseEntity.ok(operationService.finishedOperation(request.getUid()));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteOperation(OperationDto request) {
        operationService.delete(request.getUid());
        return ResponseEntity.noContent().build();
    }
}
