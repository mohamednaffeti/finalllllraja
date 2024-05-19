package com.cpg.mutuelle.entities;

import com.cpg.mutuelle.entities.enumerations.SituationDossier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DossierSoin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numDossier;
    private Double montantTotal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSoin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDepot;
    @Enumerated(value = EnumType.STRING)
    private SituationDossier situationDossier=SituationDossier.En_Cours;

    @ManyToOne(fetch = FetchType.EAGER)
    private AyantsDroits ayantsDroits;
    @ManyToOne(fetch = FetchType.EAGER)
    private Adherent adherent;

    @OneToOne(mappedBy = "dossierSoin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Remboursement remboursement;




}
