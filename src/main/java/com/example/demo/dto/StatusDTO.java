package com.example.demo.dto;

import com.example.demo.entity.StatusLibelle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private Long idStatus;
    private StatusLibelle libelle;
}
