package com.cpg.mutuelle.repositories;

import com.cpg.mutuelle.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
    List<Reclamation> findByAdherent_Id(Long id);
}
