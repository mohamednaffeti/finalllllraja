package com.cpg.mutuelle.controllers;

import com.cpg.mutuelle.entities.AyantsDroits;
import com.cpg.mutuelle.services.IAyantsDroitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ayantDroits")
@CrossOrigin(origins = "*")
public class AyantsDroitsController {
    @Autowired
    private IAyantsDroitService ayantsDroitService;

    @GetMapping("/getByAdherent/{idAdherent}")
    public ResponseEntity<List<AyantsDroits>> getAyantsDroitsByAdherent(@PathVariable Long idAdherent) {
        List<AyantsDroits> ayantsDroits = ayantsDroitService.findByAdherent(idAdherent);
        return new ResponseEntity<>(ayantsDroits, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AyantsDroits>> getAllAyantsDroits() {
        List<AyantsDroits> ayantsDroits = ayantsDroitService.getAll();
        return new ResponseEntity<>(ayantsDroits, HttpStatus.OK);
    }
}
