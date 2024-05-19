package com.cpg.mutuelle.repositories;

import com.cpg.mutuelle.entities.DossierSoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierSoinRepository extends JpaRepository<DossierSoin,Long> {
    List<DossierSoin> findByAyantsDroitsId(Long id);
    List<DossierSoin> findByAdherent_Id(Long id);
}
