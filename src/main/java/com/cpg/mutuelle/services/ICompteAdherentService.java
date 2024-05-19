package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.CompteAdherent;
import com.cpg.mutuelle.entities.dto.RegisterDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ICompteAdherentService {
    List<CompteAdherent> getAllACpAdherents();
    void createAdmin(CompteAdherent admin);
    CompteAdherent createCpAdherent(RegisterDTO cpAdherent);

    void deleteCpAdherent(Long id);
    CompteAdherent findById(Long id);
    UserDetails loadUserByUsername(String matricule);
    boolean forgetPassword(String matricule);
    CompteAdherent updatePassword(Long id , String newPassword);

}
