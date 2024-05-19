package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Adherent;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IAdherentService {
    List<Adherent> getAllAdherent();
    Adherent createAdherent(Adherent adherent);
    Adherent updateAdherent(Adherent adherent);
    void deleteAdherent(Long id);
    Adherent findById(Long id);
    Adherent loadUserByMatricule(String matricule);

}
