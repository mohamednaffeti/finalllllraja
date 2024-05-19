package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Cotisation;

import java.util.List;

public interface ICotisationService {
    List<Cotisation> getAll();
    List<Cotisation> getByAdherent(Long idAdherent);
    Cotisation getRecentById(Long idAdherent);
}
