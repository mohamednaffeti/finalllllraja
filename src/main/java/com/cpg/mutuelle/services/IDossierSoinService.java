
package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.DossierSoin;

import java.util.List;

public interface IDossierSoinService {
    List<DossierSoin> getByAyantsDroits(Long idAyantDroit);
    List<DossierSoin> getAll();
    List<DossierSoin> getByAdherent(Long id);
}
