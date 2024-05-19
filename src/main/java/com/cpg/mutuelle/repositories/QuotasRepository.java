package com.cpg.mutuelle.repositories;

import com.cpg.mutuelle.entities.Quotas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotasRepository extends JpaRepository<Quotas,Long> {
    List<Quotas> findByAyantsDroitsId(Long id);

    List<Quotas> findByAdherent_Id(Long id);
}
