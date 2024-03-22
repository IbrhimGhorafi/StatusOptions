package com.example.demo.utils;

import com.example.demo.dto.ContributionDTO;
import com.example.demo.dto.OperationDTO;
import com.example.demo.dto.StatusDTO;
import com.example.demo.entity.Contribution;
import com.example.demo.entity.Operation;
import com.example.demo.entity.Status;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Mapper {
    private ModelMapper modelMapper;
    public StatusDTO convertToStatusDTO(Status status) {
        return modelMapper.map(status, StatusDTO.class);
    }

    public Status convertToStatusEntity(StatusDTO statusDTO) {
        return modelMapper.map(statusDTO, Status.class);
    }

    public OperationDTO convertToOperationDTO(Operation operation) {
        return modelMapper.map(operation, OperationDTO.class);
    }

    public Operation convertToOperationEntity(OperationDTO operationDTO) {
        return modelMapper.map(operationDTO, Operation.class);
    }

    public ContributionDTO convertToContributionDTO(Contribution contribution) {
        return modelMapper.map(contribution, ContributionDTO.class);
    }

    public Contribution convertToContributionEntity(ContributionDTO contributionDTO) {
        return modelMapper.map(contributionDTO, Contribution.class);
    }
}
