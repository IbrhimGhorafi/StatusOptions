package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContributionDTO {
    private Long idContribution;
    private String nom;
    private String prenom;
    private String email;
    private String pays;
    private String ville;
    private String telephone;
}
