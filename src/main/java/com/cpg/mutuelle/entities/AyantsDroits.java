package com.cpg.mutuelle.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AyantsDroits implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDeNaissance;
    private String type;
    private String Situation;


    @ManyToOne(fetch = FetchType.EAGER)
    private Adherent adherent;

    @OneToMany(mappedBy = "ayantsDroits", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Quotas> quotas;
    @OneToMany(mappedBy = "ayantsDroits", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DossierSoin> dossierSoins;
}
