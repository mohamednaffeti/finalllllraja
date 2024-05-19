package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.dto.CotisationDTO;

public interface ITraitementCotisation {
    CotisationDTO getCotisation(Long idAdherent);
    String getMontantCotisation(Long idAdherent,int nbr_period);
}
