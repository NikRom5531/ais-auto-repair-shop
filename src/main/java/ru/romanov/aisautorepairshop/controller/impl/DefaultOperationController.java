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
import ru.romanov.aisautorepairshop.model.entity.Operation;
import ru.romanov.aisautorepairshop.service.OperationService;
import ru.romanov.aisautorepairshop.web.mapper.OperationMapper;
import ru.romanov.aisautorepairshop.web.mapper.UidMapper;
import ru.romanov.aisautorepairshop.web.request.OperationRequest;
import ru.romanov.aisautorepairshop.web.request.UidRequest;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/operations")
public class DefaultOperationController implements OperationController {
    private final OperationService operationService;

    @Override
    @PostMapping
    public ResponseEntity<Operation> createOperation(OperationRequest request) {
        return ResponseEntity.status(201).body(operationService.createOperation(OperationMapper.INSTANCE.toPayload(request)));
    }

    @Override
    @GetMapping
    public ResponseEntity<Operation> getOperationById(UidRequest request) {
        return ResponseEntity.ok(operationService.getOperationById(UidMapper.INSTANCE.toPayload(request).getUid()));
    }

    @Override
    @GetMapping("/all")
    public List<Operation> getAllOperations() {
        return operationService.getAllOperations();
    }

    @Override
    @GetMapping("/order")
    public List<Operation> getAllOperationsByOrderId(UidRequest request) {
        return operationService.getAllOperationsByOrderId(UidMapper.INSTANCE.toPayload(request).getUid());
    }

    @Override
    @GetMapping("/employee")
    public List<Operation> getAllOperationsByEmployeeId(UidRequest request) {
        return operationService.getAllOperationsByEmployeeId(UidMapper.INSTANCE.toPayload(request).getUid());
    }

    @Override
    @PatchMapping
    public ResponseEntity<Operation> finishedOperation(UidRequest request) {
        return ResponseEntity.ok(operationService.finishedOperation(UidMapper.INSTANCE.toPayload(request).getUid()));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteOperation(UidRequest request) {
        operationService.delete(UidMapper.INSTANCE.toPayload(request).getUid());
        return ResponseEntity.noContent().build();
    }
}
