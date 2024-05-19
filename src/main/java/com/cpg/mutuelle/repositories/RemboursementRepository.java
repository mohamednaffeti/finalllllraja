package com.cpg.mutuelle.repositories;

import com.cpg.mutuelle.entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement,Long> {
    Remboursement findByDossierSoinId(Long idDossier);
}
