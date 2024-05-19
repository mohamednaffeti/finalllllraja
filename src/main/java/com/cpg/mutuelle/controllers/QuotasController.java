package com.cpg.mutuelle.controllers;


import com.cpg.mutuelle.entities.Quotas;
import com.cpg.mutuelle.services.IQuotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotas")
@CrossOrigin(origins = "*")
public class QuotasController {
    @Autowired
    private IQuotasService quotasService;

    @GetMapping("/QuotasByAdherent/{id}")
    public ResponseEntity<List<Quotas>> getQuotasByAdherent(@PathVariable Long id) {
        List<Quotas> quotas = quotasService.getByAdherent(id);
        return new ResponseEntity<>(quotas, HttpStatus.OK);
    }

    @GetMapping("/QuotasByAyantDroit/{idAyantDroit}")
    public ResponseEntity<List<Quotas>> getQuotasByAyantsDroits(@PathVariable Long idAyantDroit) {
        List<Quotas> quotas = quotasService.getByAyantsDroits(idAyantDroit);
        return new ResponseEntity<>(quotas, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Quotas>> getAllQuotas() {
        List<Quotas> quotas = quotasService.getAll();
        return new ResponseEntity<>(quotas, HttpStatus.OK);
    }
}
