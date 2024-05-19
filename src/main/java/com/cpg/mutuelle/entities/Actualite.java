package com.cpg.mutuelle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actualite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titreActualite;
    private String contenuActualite;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePublication;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateExpiration;



    @OneToMany(mappedBy = "actualite", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Image> images;
}
