package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.AyantsDroits;

import java.util.List;

public interface IAyantsDroitService {
    List<AyantsDroits> findByAdherent(Long idAdherent);
    List<AyantsDroits> getAll();

}
