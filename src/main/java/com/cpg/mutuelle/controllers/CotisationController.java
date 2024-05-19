package com.cpg.mutuelle.controllers;

import com.cpg.mutuelle.entities.Cotisation;
import com.cpg.mutuelle.services.ICotisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cotisations")
@CrossOrigin(origins = "*")
public class CotisationController {
    @Autowired
    ICotisationService cotisationService;
    @GetMapping("/getAll")
    public ResponseEntity<List<Cotisation>> getAllCotisations() {
        List<Cotisation> cotisations = cotisationService.getAll();
        return new ResponseEntity<>(cotisations, HttpStatus.OK);
    }
    @GetMapping("/getByAdherent/{idAdherent}")
    public ResponseEntity<List<Cotisation>> getCotisationsByAdherent(@PathVariable Long idAdherent) {
        List<Cotisation> cotisations = cotisationService.getByAdherent(idAdherent);
        return new ResponseEntity<>(cotisations, HttpStatus.OK);
    }

}
