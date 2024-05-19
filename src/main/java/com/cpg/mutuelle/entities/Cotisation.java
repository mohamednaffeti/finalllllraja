package com.cpg.mutuelle.entities;

import com.cpg.mutuelle.entities.enumerations.TypeCotisation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cotisation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double montant;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFin;
    private int nbr_periode;
    @Enumerated(value =EnumType.STRING)
    private TypeCotisation cotisation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCotisation;
    @ManyToOne(fetch = FetchType.EAGER)
    private Adherent adherent;
}
