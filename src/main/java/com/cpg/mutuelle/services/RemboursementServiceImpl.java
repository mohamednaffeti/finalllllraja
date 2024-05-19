package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Remboursement;
import com.cpg.mutuelle.repositories.RemboursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemboursementServiceImpl implements IRemboursementService {
    @Autowired
    private RemboursementRepository remboursementRepository;
    @Override
    public List<Remboursement> getAll() {
        return remboursementRepository.findAll();
    }

    @Override
    public Remboursement getByDossierSoin(Long idDossier) {
        return remboursementRepository.findByDossierSoinId(idDossier);
    }
}
