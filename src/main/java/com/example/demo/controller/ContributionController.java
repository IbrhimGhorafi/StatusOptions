package com.example.demo.controller;

import com.example.demo.dto.ContributionDTO;
import com.example.demo.service.serviceContrat.ContributionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contributions")
@AllArgsConstructor
public class ContributionController {

    private final ContributionService contributionService;

    @GetMapping
    public List<ContributionDTO> getAllContributions() {
        return contributionService.getAllContributions();
    }

    @GetMapping("/{email}")
    public ContributionDTO getContributionByEmail(@PathVariable String email) {
        return contributionService.getContributionByEmail(email);
    }

    @PostMapping
    public ContributionDTO createContribution(@RequestBody ContributionDTO contributionDTO) {
        return contributionService.createContribution(contributionDTO);
    }

    @PutMapping("/{id}")
    public ContributionDTO updateContribution(@PathVariable Long id, @RequestBody ContributionDTO contributionDTO) {
        return contributionService.updateContribution(id, contributionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteContribution(@PathVariable Long id) {
        contributionService.deleteContribution(id);
    }
}
