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
    public ResponseEntity<Operation> getOperationById(OperationDto request) {
        return ResponseEntity.ok(operationService.getOperationById(request.getUid()));
    }

    @Override
    @GetMapping("/all")
    public List<Operation> getAllOperations() {
        return operationService.getAllOperations();
    }

    @Override
    @GetMapping("/order")
    public List<Operation> getAllOperationsByOrderId(OperationDto request) {
        return operationService.getAllOperationsByOrderId(request.getUid());
    }

    @Override
    @GetMapping("/employee")
    public List<Operation> getAllOperationsByEmployeeId(OperationDto request) {
        return operationService.getAllOperationsByEmployeeId(request.getUid());
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
