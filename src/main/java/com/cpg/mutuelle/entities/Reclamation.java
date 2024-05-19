package com.cpg.mutuelle.entities;

import com.cpg.mutuelle.entities.enumerations.StatusReclamation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String titre;
    @Column(length = 1000)
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-ss-mm")
    private LocalDateTime dateReclamation = LocalDateTime.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-ss-mm")
    private LocalDateTime dateUpdate = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private StatusReclamation statut = StatusReclamation.OUVERTE;
    private boolean lu=false;
    @ManyToOne(fetch = FetchType.EAGER)
    private Adherent adherent;
}
