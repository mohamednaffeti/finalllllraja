package com.cpg.mutuelle.controllers;

import com.cpg.mutuelle.entities.Remboursement;
import com.cpg.mutuelle.services.IRemboursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remboursement")
@CrossOrigin(origins = "*")
public class RemboursementController {
    @Autowired
    private IRemboursementService remboursementService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Remboursement>> getAllRemboursements() {
        List<Remboursement> remboursements = remboursementService.getAll();
        return new ResponseEntity<>(remboursements, HttpStatus.OK);
    }

    @GetMapping("/getByDossierSoin/{idDossier}")
    public ResponseEntity<Remboursement> getRemboursementByDossierSoin(@PathVariable Long idDossier) {
        Remboursement remboursement = remboursementService.getByDossierSoin(idDossier);
        if (remboursement != null) {
            return new ResponseEntity<>(remboursement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
