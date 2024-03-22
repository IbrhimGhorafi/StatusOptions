package com.example.demo.service;

import com.example.demo.dto.ContributionDTO;
import com.example.demo.entity.Contribution;
import com.example.demo.repository.ContributionRepository;
import com.example.demo.service.serviceContrat.ContributionService;
import com.example.demo.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContributionServiceImpl implements ContributionService {

    private final ContributionRepository contributionRepository;
    private  Mapper mapper;
    @Override
    public List<ContributionDTO> getAllContributions() {
        List<Contribution> contributions = contributionRepository.findAll();
        return contributions.stream()
                .map(mapper::convertToContributionDTO)
                .collect(Collectors.toList());    }

    @Override
    public ContributionDTO getContributionByEmail(String email) {
        Contribution contribution = contributionRepository.findByEmail(email);
        if (contribution == null) {
            throw new IllegalArgumentException("Contribution not found with email: " + email);
        }
        return mapper.convertToContributionDTO(contribution);
    }



    @Override
    public ContributionDTO createContribution(ContributionDTO contributionDTO) {
        Contribution existingContribution = contributionRepository.findByEmail(contributionDTO.getEmail());
        if (existingContribution != null) {
            throw new IllegalArgumentException("Contribution with email " + contributionDTO.getEmail() + " already exists");
        }

        Contribution newContribution = mapper.convertToContributionEntity(contributionDTO);
        contributionRepository.save(newContribution);

        return mapper.convertToContributionDTO(newContribution);
    }


    @Override
    public ContributionDTO updateContribution(Long id, ContributionDTO contributionDTO) {
        Contribution existingContribution = contributionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contribution not found with id: " + id));

        existingContribution.setNom(contributionDTO.getNom());
        existingContribution.setPrenom(contributionDTO.getPrenom());
        existingContribution.setEmail(contributionDTO.getEmail());
        existingContribution.setPays(contributionDTO.getPays());
        existingContribution.setVille(contributionDTO.getVille());
        existingContribution.setTelephone(contributionDTO.getTelephone());

        contributionRepository.save(existingContribution);

        return mapper.convertToContributionDTO(existingContribution);
    }


    @Override
    public void deleteContribution(Long id) {
        Contribution existingContribution = contributionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contribution not found with id: " + id));

        contributionRepository.delete(existingContribution);
    }

}
