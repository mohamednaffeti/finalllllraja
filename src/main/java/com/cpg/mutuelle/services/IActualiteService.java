package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Actualite;

import java.util.List;

public interface IActualiteService {
    List<Actualite> getAll();
    Actualite addActualite(Actualite actualite);
    Actualite updateActualite(Actualite newActualite);
    void deleteActualite(Long id);
}
