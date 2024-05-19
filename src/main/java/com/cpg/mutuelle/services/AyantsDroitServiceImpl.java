package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.AyantsDroits;
import com.cpg.mutuelle.repositories.AyantDroitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AyantsDroitServiceImpl implements IAyantsDroitService {
    @Autowired
    AyantDroitsRepository ayantDroitsRepository;
    @Override
    public List<AyantsDroits> findByAdherent(Long idAdherent) {
        return ayantDroitsRepository.findAyantsDroitsByAdherent_Id(idAdherent);
    }

    @Override
    public List<AyantsDroits> getAll() {
        return ayantDroitsRepository.findAll();
    }
}
