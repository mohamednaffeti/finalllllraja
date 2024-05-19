package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Adherent;
import com.cpg.mutuelle.entities.Reclamation;
import com.cpg.mutuelle.entities.enumerations.StatusReclamation;
import com.cpg.mutuelle.exceptions.DataNotFoundException;
import com.cpg.mutuelle.repositories.AdherentRepository;
import com.cpg.mutuelle.repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReclamationServiceImpl implements IReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;
    @Autowired
    AdherentRepository adherentRepository;
    @Override
    public List<Reclamation> getAll() {
        return reclamationRepository.findAll();
    }

    @Override
    public List<Reclamation> findByAdherent(Long id) {
        return reclamationRepository.findByAdherent_Id(id);
    }

    @Override
    public List<Reclamation> reclamationsNonLues() {
        return this.getAll().stream().filter(reclamation -> !reclamation.isLu()).toList();
    }

    @Override
    public Reclamation updateLuStatus(Long id) {
        Reclamation reclamation = reclamationRepository.findById(id).orElse(null);
        if(reclamation==null){
            throw new DataNotFoundException("reclamation not found");
        }else{
            reclamation.setLu(true);
            return reclamationRepository.save(reclamation);
        }

    }

    @Override
    public Reclamation updateStatusReclamation(Long id, StatusReclamation newStatus) {
        Reclamation reclamation = reclamationRepository.findById(id).orElse(null);
        if(reclamation==null){
            throw new DataNotFoundException("reclamation not found");
        }else if(reclamation.getStatut()==newStatus){
            throw new DataNotFoundException("it's the same status");
        }else{
            reclamation.setStatut(newStatus);
            reclamation.setDateUpdate(LocalDateTime.now());
            return reclamationRepository.save(reclamation);
        }
    }

    @Override
    public Reclamation addReclamation(Long id, Reclamation reclamation) {
        Adherent adherent = adherentRepository.findById(id).orElse(null);
        if(adherent==null){
            throw new DataNotFoundException("Adherent does not exist");
        }else{
            reclamation.setAdherent(adherent);
            return reclamationRepository.save(reclamation);
        }
    }

    @Override
    public Reclamation updateReclamation(Reclamation newReclamation) {
        Reclamation existingReclamation = reclamationRepository.findById(newReclamation.getId()).orElse(null);
        if(existingReclamation == null) {
            throw new DataNotFoundException("Reclamation not found");
        } else {
            existingReclamation.setTitre(newReclamation.getTitre());
            existingReclamation.setDescription(newReclamation.getDescription());
            existingReclamation.setDateUpdate(LocalDateTime.now());
            return reclamationRepository.save(existingReclamation);
        }
    }

    @Override
    public void deleteReclamation(long id) {
        reclamationRepository.deleteById(id);
    }
}
