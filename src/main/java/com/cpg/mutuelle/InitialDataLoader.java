package com.cpg.mutuelle;


import com.cpg.mutuelle.entities.CompteAdherent;
import com.cpg.mutuelle.entities.enumerations.Gender;
import com.cpg.mutuelle.entities.enumerations.Role;
import com.cpg.mutuelle.repositories.CompteAdherentRepository;
import com.cpg.mutuelle.services.ICompteAdherentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitialDataLoader implements CommandLineRunner {

    private final ICompteAdherentService adherentService;

    private final CompteAdherentRepository adherentRepository;

    public InitialDataLoader(ICompteAdherentService adherentService, CompteAdherentRepository adherentRepository) {
        this.adherentService = adherentService;
        this.adherentRepository = adherentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(adherentRepository.findByMatricule("admin").isPresent()){
            System.out.println("L'admin existe déjà, aucune action nécessaire.");
        }else{
            CompteAdherent admin = CompteAdherent.builder().build();
            admin.setMatricule("admin");
            admin.setCin("00000000");
            admin.setPassword("admin");
            admin.setNom("Naffeti");
            admin.setPrenom("Mohamed");
            admin.setRole(Role.ADMIN);
            admin.setSexe(Gender.Homme);
            admin.setEmail("mohamednaffati08@gmail.com");
            admin.setDateDeNaissance(LocalDate.of(1975 , 1 , 1));
            adherentService.createAdmin(admin);
            System.out.println("Admin ajouté avec succès !");
        }
    }
}
