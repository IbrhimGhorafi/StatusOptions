package com.example.demo.service.serviceContrat;

import com.example.demo.dto.ContributionDTO;

import java.util.List;

public interface ContributionService {
    List<ContributionDTO> getAllContributions();

    ContributionDTO getContributionByEmail(String email);

    ContributionDTO createContribution(ContributionDTO contribution);

    ContributionDTO updateContribution(Long id, ContributionDTO contribution);

    void deleteContribution(Long id);
}
