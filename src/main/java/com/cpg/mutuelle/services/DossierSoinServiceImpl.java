package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.DossierSoin;
import com.cpg.mutuelle.repositories.DossierSoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierSoinServiceImpl implements IDossierSoinService {
    @Autowired
    DossierSoinRepository dossierSoinRepository;
    @Override
    public List<DossierSoin> getByAyantsDroits(Long idAyantDroit) {
        return dossierSoinRepository.findByAyantsDroitsId(idAyantDroit);
    }
    @Override
    public List<DossierSoin> getByAdherent(Long id) {
        return dossierSoinRepository.findByAdherent_Id(id);
    }
    @Override
    public List<DossierSoin> getAll() {
        return dossierSoinRepository.findAll();
    }
}
