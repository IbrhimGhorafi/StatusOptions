package com.example.demo.service;

import com.example.demo.dto.StatusDTO;
import com.example.demo.entity.Status;
import com.example.demo.entity.StatusLibelle;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.serviceContrat.StatusService;
import com.example.demo.utils.Mapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;
    private final Mapper mapper;

    @Override
    public List<StatusDTO> getAllStatus() {
        List<Status> statuses = statusRepository.findAll();
        if (statuses.isEmpty()) {
            throw new RuntimeException("No statuses found in database");
        }
        return statuses.stream()
                .map(mapper::convertToStatusDTO)
                .toList();
    }

    @Override
    public StatusDTO getStatusByLabelle(StatusLibelle statusLibelle) {
        try {
            StatusLibelle.valueOf(statusLibelle.name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Value not exist in enum");
        }

        Status status = statusRepository.findByLibelle(statusLibelle);
        if (status == null) {
            throw new IllegalArgumentException("Value not exist in database");
        }

        return mapper.convertToStatusDTO(status);
    }


    @Override
    public StatusDTO createStatus(StatusDTO statusDTO) {
        Status status = statusRepository.findByLibelle(statusDTO.getLibelle());
        if (status != null) {
            throw new IllegalArgumentException("Status already exist in database");
        }
        Status newStatus=mapper.convertToStatusEntity(statusDTO);
        statusRepository.save(newStatus);
        return null;
    }

    @Override
    public StatusDTO updateStatus(Long id, StatusDTO statusDTO) {
        Status existingStatus = statusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Status not found with id: " + id));

        existingStatus.setLibelle(statusDTO.getLibelle());
        statusRepository.save(existingStatus);

        return mapper.convertToStatusDTO(existingStatus);
    }


    @Override
    public void deleteStatus(Long id) {
        Status existingStatus = statusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Status not found with id: " + id));
        if (existingStatus != null) {
            statusRepository.deleteById(id);
        }
    }

    @PostConstruct
    public void createD() {
        List<StatusLibelle> defaultStatuses = Arrays.asList(StatusLibelle.EN_COURS, StatusLibelle.EN_VALIDATION, StatusLibelle.REJETE, StatusLibelle.ACCEPT);
        for (StatusLibelle statusLibelle : defaultStatuses) {
            if (statusRepository.findByLibelle(statusLibelle) == null) {
                Status status = new Status();
                status.setLibelle(statusLibelle);
                statusRepository.save(status);
            }
        }
    }


}
