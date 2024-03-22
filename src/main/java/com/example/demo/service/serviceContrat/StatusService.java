package com.example.demo.service.serviceContrat;

import com.example.demo.dto.StatusDTO;
import com.example.demo.entity.StatusLibelle;

import java.util.List;

public interface StatusService {
    List<StatusDTO> getAllStatus();

    StatusDTO getStatusByLabelle(StatusLibelle statusLibelle);

    StatusDTO createStatus(StatusDTO status);

    StatusDTO updateStatus(Long id, StatusDTO status);

    void deleteStatus(Long id);
}
