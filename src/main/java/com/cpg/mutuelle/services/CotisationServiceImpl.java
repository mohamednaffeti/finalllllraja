package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Cotisation;
import com.cpg.mutuelle.repositories.CotisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class CotisationServiceImpl implements ICotisationService {
    @Autowired
    CotisationRepository cotisationRepository;
    @Override
    public List<Cotisation> getAll() {
        return cotisationRepository.findAll();
    }

    @Override
    public List<Cotisation> getByAdherent(Long idAdherent) {
        return cotisationRepository.findByAdherent_Id(idAdherent);
    }

    @Override
    public Cotisation getRecentById(Long idAdherent) {
        return this.getByAdherent(idAdherent)
                .stream()
                .max(Comparator.comparing(Cotisation::getDateFin))
                .orElse(null);
    }
}
