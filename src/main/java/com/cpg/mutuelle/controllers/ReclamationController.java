package com.cpg.mutuelle.controllers;

import com.cpg.mutuelle.entities.Reclamation;
import com.cpg.mutuelle.entities.enumerations.StatusReclamation;
import com.cpg.mutuelle.services.IReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reclamation")
@CrossOrigin(origins = "*")
public class ReclamationController {
    @Autowired
    private IReclamationService reclamationService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Reclamation>> getAllReclamations() {
        List<Reclamation> reclamations = reclamationService.getAll();
        return new ResponseEntity<>(reclamations, HttpStatus.OK);
    }

    @GetMapping("/getByAdherentId/{adherentId}")
    public ResponseEntity<List<Reclamation>> getReclamationsByAdherent(@PathVariable("adherentId") Long adherentId) {
        List<Reclamation> reclamations = reclamationService.findByAdherent(adherentId);
        return new ResponseEntity<>(reclamations, HttpStatus.OK);
    }
    @PutMapping("/updateLuStatus/{id}")
    public ResponseEntity<Reclamation> updateLuStatus(@PathVariable Long id) {
        Reclamation updatedReclamation = reclamationService.updateLuStatus(id);
        return new ResponseEntity<>(updatedReclamation, HttpStatus.OK);
    }
    @PutMapping("/updateStatusReclamation/{id}/{newStatus}")
    public ResponseEntity<Reclamation> updateStatusReclamation(@PathVariable Long id, @PathVariable StatusReclamation newStatus) {
        this.updateLuStatus(id);
        Reclamation updatedReclamation = reclamationService.updateStatusReclamation(id, newStatus);
        return new ResponseEntity<>(updatedReclamation, HttpStatus.OK);
    }
    @PostMapping("/add/{adherentId}")
    public ResponseEntity<Reclamation> addReclamation(@PathVariable Long adherentId, @RequestBody Reclamation reclamation) {
        Reclamation addedReclamation = reclamationService.addReclamation(adherentId, reclamation);
        return new ResponseEntity<>(addedReclamation, HttpStatus.CREATED);
    }
    @PutMapping("/updateReclamation")
    public ResponseEntity<Reclamation> updateReclamation(@RequestBody Reclamation newReclamation) {
        Reclamation updatedReclamation = reclamationService.updateReclamation(newReclamation);
        return new ResponseEntity<>(updatedReclamation, HttpStatus.OK);
    }

    @DeleteMapping("/deleteReclamation/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable long id) {
        reclamationService.deleteReclamation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
