package com.cpg.mutuelle.controllers;

import com.cpg.mutuelle.entities.dto.CotisationDTO;
import com.cpg.mutuelle.entities.dto.MontantDTO;
import com.cpg.mutuelle.services.ITraitementCotisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traitement")
@CrossOrigin(origins = "*")
public class TraitementCotisationController {
    @Autowired
    private ITraitementCotisation traitementCotisationService;

    @GetMapping("/{idAdherent}")
    public ResponseEntity<CotisationDTO> getCotisation(@PathVariable Long idAdherent) {
        CotisationDTO cotisationDTO = traitementCotisationService.getCotisation(idAdherent);
        if (cotisationDTO != null) {
            return new ResponseEntity<>(cotisationDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/montant/{idAdherent}/{nbrPeriod}")
    public ResponseEntity<MontantDTO> getMontantCotisation(@PathVariable Long idAdherent, @PathVariable int nbrPeriod) {
        String montantCotisation = traitementCotisationService.getMontantCotisation(idAdherent, nbrPeriod);

        MontantDTO montantDTO = new MontantDTO(montantCotisation);
        return new ResponseEntity<>(montantDTO, HttpStatus.OK);
    }
}
