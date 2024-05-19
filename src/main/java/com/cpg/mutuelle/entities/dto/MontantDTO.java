package com.cpg.mutuelle.entities.dto;

public class MontantDTO {
    private String montant;

    public MontantDTO(String montant) {
        this.montant = montant;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
}