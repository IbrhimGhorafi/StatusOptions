package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    private Date dateCreation;
    private Date dateMiseJour;
    @ManyToOne(cascade = CascadeType.ALL)
    private Status status;
    @ManyToOne(cascade = CascadeType.ALL)
    private Contribution contribution;
}
