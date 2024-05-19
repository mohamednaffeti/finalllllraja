package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Adherent;
import com.cpg.mutuelle.exceptions.DataNotFoundException;
import com.cpg.mutuelle.repositories.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdherentServiceImpl implements IAdherentService {
    @Autowired
    AdherentRepository adherentRepository;
    @Override
    public List<Adherent> getAllAdherent() {
        return adherentRepository.findAll();
    }

    @Override
    public Adherent createAdherent(Adherent adherent) {
        if(adherentRepository.findByMatricule(adherent.getMatricule()).isPresent()){
            throw new DataNotFoundException("Matricule is already taken to another adherent");
        }else{
            return adherentRepository.save(adherent);
        }

    }

    @Override
    public Adherent updateAdherent(Adherent adherent) {
        Optional<Adherent> existingAdherentOptional = adherentRepository.findById(adherent.getId());

        if (existingAdherentOptional.isPresent()) {
            Adherent existingAdherent = existingAdherentOptional.get();


            existingAdherent.setNom(adherent.getNom());
            existingAdherent.setPrenom(adherent.getPrenom());
            existingAdherent.setMatricule(adherent.getMatricule());
            existingAdherent.setMail(adherent.getMail());
            existingAdherent.setTel(adherent.getTel());
            existingAdherent.setType(adherent.getType());
            existingAdherent.setEtatCivil(adherent.getEtatCivil());
            existingAdherent.setSexe(adherent.getSexe());
            existingAdherent.setAssiette(adherent.getAssiette());
            existingAdherent.setDateDepartRetraite(adherent.getDateDepartRetraite());
            existingAdherent.setSalaireCNSS(adherent.getSalaireCNSS());
            existingAdherent.setDateDeNaissance(adherent.getDateDeNaissance());
            existingAdherent.setCin(adherent.getCin());
            existingAdherent.setSexe(adherent.getSexe());



            return adherentRepository.save(existingAdherent);
        } else {
            return null;
        }
    }

    @Override
    public void deleteAdherent(Long id) {
        Adherent existingAdherent = adherentRepository.findById(id).orElse(null);
        if (existingAdherent != null) {
            existingAdherent.setActive(false);
            adherentRepository.save(existingAdherent);
        }
    }

    @Override
    public Adherent findById(Long id) {
        return adherentRepository.findById(id).orElse(null);
    }

    @Override
    public Adherent loadUserByMatricule(String matricule) {
        return adherentRepository.findByMatricule(matricule).orElse(null);
    }


}
