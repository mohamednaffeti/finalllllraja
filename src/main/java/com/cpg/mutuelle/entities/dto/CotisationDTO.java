package com.cpg.mutuelle.entities.dto;

import com.cpg.mutuelle.entities.enumerations.TypeCotisation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CotisationDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebutComptage;
    @Enumerated(value =EnumType.STRING)
    private TypeCotisation typeCotisation;
    private int nbr_periode;
    private double montantCotisation;
    private double montantcnss;
    private String indice;

}
