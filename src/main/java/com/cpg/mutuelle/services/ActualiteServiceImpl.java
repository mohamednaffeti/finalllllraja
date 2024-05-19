package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Actualite;
import com.cpg.mutuelle.repositories.ActualiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActualiteServiceImpl implements IActualiteService {
    @Autowired
    private ActualiteRepository actualiteRepository;
    @Override
    public List<Actualite> getAll() {
        return actualiteRepository.findAll();
    }

    @Override
    public Actualite addActualite(Actualite actualite) {

        return actualiteRepository.save(actualite);
    }

    @Override
    public Actualite updateActualite(Actualite newActualite) {
        Optional<Actualite> optionalActualite = actualiteRepository.findById(newActualite.getId());
        if (optionalActualite.isPresent()) {
            Actualite existingActualite = optionalActualite.get();
            existingActualite.setTitreActualite(newActualite.getTitreActualite());
            existingActualite.setContenuActualite(newActualite.getContenuActualite());
            return actualiteRepository.save(existingActualite);
        } else {
            return null;
        }
    }

    @Override
    public void deleteActualite(Long id) {
        actualiteRepository.deleteById(id);
    }
}
