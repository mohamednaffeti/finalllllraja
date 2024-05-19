package com.cpg.mutuelle.controllers;

import com.cpg.mutuelle.entities.Adherent;
import com.cpg.mutuelle.services.IAdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adherent")
@CrossOrigin(origins = "*")
public class AdherentController {
    @Autowired
    private IAdherentService adherentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Adherent>> getAllAdherents() {
        List<Adherent> adherents = adherentService.getAllAdherent().stream()
                .filter(Adherent::getActive).toList();
        return new ResponseEntity<>(adherents, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Adherent> getAdherentById(@PathVariable Long id) {
        Adherent adherent = adherentService.findById(id);
        return new ResponseEntity<>(adherent, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Adherent> createAdherent(@RequestBody Adherent adherent) {
        adherent.setActive(true);
        Adherent createdAdherent = adherentService.createAdherent(adherent);
        return new ResponseEntity<>(createdAdherent, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Adherent> updateAdherent(@RequestBody Adherent adherent) {
        Adherent updatedAdherent = adherentService.updateAdherent(adherent);
        return new ResponseEntity<>(updatedAdherent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAdherent(@PathVariable Long id) {
        adherentService.deleteAdherent(id);
    }


}
