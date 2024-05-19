package com.cpg.mutuelle.controllers;

import com.cpg.mutuelle.entities.DossierSoin;
import com.cpg.mutuelle.services.IDossierSoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/dossierSoin")
@CrossOrigin(origins = "*")
public class DossierSoinController {
    @Autowired
    private IDossierSoinService dossierSoinService;


    @GetMapping("/getByAyantDroit/{idAyantDroit}")
    public ResponseEntity<List<DossierSoin>> getDossiersByAyantDroit(@PathVariable Long idAyantDroit) {
        List<DossierSoin> dossiers = dossierSoinService.getByAyantsDroits(idAyantDroit);
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }

    @GetMapping("/getByAdherent/{id}")
    public ResponseEntity<List<DossierSoin>> getDossiersByAdherent(@PathVariable Long id) {
        List<DossierSoin> dossiers = dossierSoinService.getByAdherent(id);
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }

    @GetMapping("/getAllDossiers")
    public ResponseEntity<List<DossierSoin>> getAllDossiers() {
        List<DossierSoin> dossiers = dossierSoinService.getAll();
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }
}
