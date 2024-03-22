package com.example.demo.controller;

import com.example.demo.dto.OperationDTO;
import com.example.demo.service.serviceContrat.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
@AllArgsConstructor
public class OperationController {

    private final OperationService operationService;



    @GetMapping
    public List<OperationDTO> getAllOperations() {
        return operationService.getAllOperations();
    }

    @GetMapping("/{id}")
    public OperationDTO getOperationById(@PathVariable Long id) {
        return operationService.getOperationById(id);
    }

    @PostMapping
    public OperationDTO createOperation(@RequestBody OperationDTO operationDTO) {
        return operationService.createOperation(operationDTO);
    }

    @PutMapping("/{id}")
    public OperationDTO updateOperation(@PathVariable Long id, @RequestBody OperationDTO operationDTO) {
        return operationService.updateOperation(id, operationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
    }
}
