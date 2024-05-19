package com.cpg.mutuelle.repositories;

import com.cpg.mutuelle.entities.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent,Long> {
    Optional<Adherent> findByMatricule(String matricule);

}
