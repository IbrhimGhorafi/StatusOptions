package com.example.demo.dto;

import com.example.demo.entity.Contribution;
import com.example.demo.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {
    private Long idOperation;
    private String libelle;
    private Date dateCreation;
    private Date dateMiseJour;
    private Status status;
    private Contribution contribution;
}
