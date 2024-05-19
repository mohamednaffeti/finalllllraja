package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Reclamation;
import com.cpg.mutuelle.entities.enumerations.StatusReclamation;

import java.util.List;

public interface IReclamationService {
    List<Reclamation> getAll();
    List<Reclamation> findByAdherent(Long id);
    List<Reclamation> reclamationsNonLues();
    Reclamation updateLuStatus(Long id);
    Reclamation updateStatusReclamation(Long id , StatusReclamation newStatus);
    Reclamation addReclamation(Long id,Reclamation reclamation);
    Reclamation updateReclamation(Reclamation newReclamation);
    void deleteReclamation(long id);

}
