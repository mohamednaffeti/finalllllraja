package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Remboursement;

import java.util.List;

public interface IRemboursementService {
    List<Remboursement> getAll();
    Remboursement getByDossierSoin(Long idDossier);
}
