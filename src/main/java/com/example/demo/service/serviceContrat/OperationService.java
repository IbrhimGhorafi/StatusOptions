package com.example.demo.service.serviceContrat;

import com.example.demo.dto.OperationDTO;

import java.util.List;

public interface OperationService {
    List<OperationDTO> getAllOperations();

    OperationDTO getOperationById(Long id);

    OperationDTO createOperation(OperationDTO operation);

    OperationDTO updateOperation(Long id, OperationDTO operation);

    void deleteOperation(Long id);
}
