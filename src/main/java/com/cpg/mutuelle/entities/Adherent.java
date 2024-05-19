package com.cpg.mutuelle.entities;

import com.cpg.mutuelle.entities.enumerations.Gender;
import com.cpg.mutuelle.entities.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

public class Adherent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String matricule;
    private String mail;
    private String tel;
    private String type;
    private String etatCivil;
    @Enumerated(value = EnumType.STRING)
    private Gender sexe;
    private double assiette;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDepartRetraite;
    private double salaireCNSS;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDeNaissance;
    private String cin;

    private Boolean active;


    @OneToOne(mappedBy = "adherent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private CompteAdherent compte;


    @OneToMany(mappedBy = "adherent", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AyantsDroits> ayantsDroits;

    @OneToMany(mappedBy = "adherent",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Quotas> quotas;

    @OneToMany(mappedBy = "adherent",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DossierSoin> dossierSoins;

    @OneToMany(mappedBy = "adherent",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reclamation> reclamations;

    @OneToMany(mappedBy = "adherent",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cotisation> cotisations;

}
