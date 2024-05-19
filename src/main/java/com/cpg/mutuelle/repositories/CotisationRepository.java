package com.cpg.mutuelle.repositories;

import com.cpg.mutuelle.entities.Cotisation;
import io.swagger.v3.oas.annotations.links.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotisationRepository extends JpaRepository<Cotisation,Long> {
    List<Cotisation> findByAdherent_Id(Long id);
}
