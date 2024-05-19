package com.cpg.mutuelle.entities.dto;

import com.cpg.mutuelle.entities.AyantsDroits;
import com.cpg.mutuelle.entities.Quotas;
import com.cpg.mutuelle.entities.enumerations.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
public class CurrentUserDto {
    private Long id;
    private String nom;
    private String prenom;
    private String matricule;
    private String mail;
    private String tel;
    private String type;
    private String etatCivil;
    private String sexe;
    private LocalDate dateDeNaissance;
    private String cin;
    private Role role;
    List<AyantsDroits> ayantsDroits;


}
