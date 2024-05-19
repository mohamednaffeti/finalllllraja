package com.cpg.mutuelle.repositories;

import com.cpg.mutuelle.entities.Actualite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualiteRepository extends JpaRepository<Actualite,Long> {

}
