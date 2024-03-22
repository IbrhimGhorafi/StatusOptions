package com.example.demo.service;

import com.example.demo.dto.OperationDTO;
import com.example.demo.entity.Contribution;
import com.example.demo.entity.Operation;
import com.example.demo.entity.Status;
import com.example.demo.repository.ContributionRepository;
import com.example.demo.repository.OperationRepository;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.serviceContrat.OperationService;
import com.example.demo.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final StatusRepository statusRepository;
    private final ContributionRepository contributionRepository;
    private final Mapper mapper;

    @Override
    public List<OperationDTO> getAllOperations() {
        List<Operation> operations = operationRepository.findAll();
        return operations.stream()
                .map(mapper::convertToOperationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDTO getOperationById(Long id) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Operation not found with id: " + id));

        return mapper.convertToOperationDTO(operation);
    }


    @Override
    public OperationDTO createOperation(OperationDTO operationDTO) {
        Status status = statusRepository.findByLibelle(operationDTO.getStatus().getLibelle());
        if (status == null) {
            throw new IllegalArgumentException("Status not found with libelle: " + operationDTO.getStatus().getLibelle());
        }

        Contribution contribution = contributionRepository.findByEmail(operationDTO.getContribution().getEmail());
        if (contribution == null) {
            throw new IllegalArgumentException("Contribution not found with email: " + operationDTO.getContribution().getEmail());
        }

        operationDTO.setDateCreation(new Date());

        Operation operation = mapper.convertToOperationEntity(operationDTO);
        operation.setStatus(status);
        operation.setContribution(contribution);
        operationRepository.save(operation);

        return mapper.convertToOperationDTO(operation);
    }


    @Override
    public OperationDTO updateOperation(Long id, OperationDTO operationDTO) {
        Operation existingOperation = operationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Operation not found with id: " + id));

        existingOperation.setStatus(operationDTO.getStatus());

        existingOperation.setDateMiseJour(new Date());

        operationRepository.save(existingOperation);

        return mapper.convertToOperationDTO(existingOperation);
    }


    @Override
    public void deleteOperation(Long id) {
        Operation existingOperation = operationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Operation not found with id: " + id));
        operationRepository.deleteById(id);
    }
}
