package com.cpg.mutuelle.entities;

import com.cpg.mutuelle.entities.enumerations.Gender;
import com.cpg.mutuelle.entities.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompteAdherent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricule;
    private String password;
    private String cin;
    private String tel;
    private String nom;
    private String prenom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDeNaissance;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Gender sexe;
    private String email;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;



}
