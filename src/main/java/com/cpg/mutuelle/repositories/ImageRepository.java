package com.cpg.mutuelle.repositories;

import com.cpg.mutuelle.entities.Actualite;
import com.cpg.mutuelle.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    Optional<Image> findByActualite(Actualite actualite);


    List<Image> findImagesByActualiteId(Long id);
}
