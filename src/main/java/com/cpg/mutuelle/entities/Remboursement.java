package com.cpg.mutuelle.entities;

import com.cpg.mutuelle.entities.enumerations.MethodeRemboursement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Remboursement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private double montant;
    @Enumerated(value = EnumType.STRING)
    private MethodeRemboursement methodeRemboursement;
    private String mois;
    private String annee;
    @OneToOne(fetch = FetchType.EAGER)
    private DossierSoin dossierSoin;
}
