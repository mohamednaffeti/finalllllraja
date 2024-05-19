package com.cpg.mutuelle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quotas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String annee;
    private String type;
    private String plafond;
    private String montantRestant;

    @ManyToOne(fetch = FetchType.EAGER)
    private AyantsDroits ayantsDroits;
    @ManyToOne(fetch = FetchType.EAGER)
    private Adherent adherent;

}
