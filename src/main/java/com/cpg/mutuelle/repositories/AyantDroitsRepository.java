package com.cpg.mutuelle.repositories;

import com.cpg.mutuelle.entities.AyantsDroits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AyantDroitsRepository extends JpaRepository<AyantsDroits,Long> {
    List<AyantsDroits> findAyantsDroitsByAdherent_Id(Long id);
}
