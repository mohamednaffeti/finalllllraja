package com.cpg.mutuelle.services;

import com.cpg.mutuelle.detailsServices.CpAdherentInfoDetails;
import com.cpg.mutuelle.entities.*;
import com.cpg.mutuelle.entities.dto.EmailSubjectDTO;
import com.cpg.mutuelle.entities.dto.FormatEmailDTO;
import com.cpg.mutuelle.entities.dto.PasswordGenerator;
import com.cpg.mutuelle.entities.dto.RegisterDTO;
import com.cpg.mutuelle.entities.enumerations.Role;
import com.cpg.mutuelle.exceptions.DataNotFoundException;
import com.cpg.mutuelle.exceptions.UserAlreadyExistException;
import com.cpg.mutuelle.mailConfig.MailConfig;
import com.cpg.mutuelle.repositories.AdherentRepository;
import com.cpg.mutuelle.repositories.CompteAdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteAdherentServiceImpl implements ICompteAdherentService, UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CompteAdherentRepository cpAdherentRepository;
    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private IReclamationService reclamationService;
    @Autowired ICotisationService cotisationService;
    @Autowired
    private MailConfig emailService;


    @Override
    public List<CompteAdherent> getAllACpAdherents() {
        return cpAdherentRepository.findAll();
    }

    @Override
    public void createAdmin(CompteAdherent admin) {
        System.out.println(admin);
        if (cpAdherentRepository.findByMatricule(admin.getMatricule()).isPresent()) {
            throw new UserAlreadyExistException("Username already exist to another user");
        } else if (adherentRepository.findByMatricule(admin.getMatricule()).isEmpty() && admin.getRole().equals(Role.ADHERENT)) {
            throw new DataNotFoundException("Adhrent not found with this matricule");
        }else{
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            cpAdherentRepository.save(admin);
        }
    }

    @Override
    public CompteAdherent createCpAdherent(RegisterDTO cp) {

        Adherent adherent =adherentRepository.findByMatricule(cp.getMatricule()).orElse(null);
        if(adherent== null){
            throw new DataNotFoundException("Adhrent not found");
        }else{
            CompteAdherent cpAdherent = new CompteAdherent();
            cpAdherent.setNom(adherent.getNom());
            cpAdherent.setPrenom(adherent.getPrenom());
            cpAdherent.setCin(adherent.getCin());
            cpAdherent.setTel(adherent.getTel());
            cpAdherent.setRole(Role.ADHERENT);
            cpAdherent.setDateDeNaissance(adherent.getDateDeNaissance());
            cpAdherent.setPassword(passwordEncoder.encode(cp.getPassword()));
            cpAdherent.setAdherent(adherentRepository.findByMatricule(cp.getMatricule()).orElse(null));
            cpAdherent.setSexe(adherent.getSexe());
            cpAdherent.setEmail(adherent.getMail());
            cpAdherent.setMatricule(cp.getMatricule());
            return cpAdherentRepository.save(cpAdherent);
        }



    }



    @Override
    public void deleteCpAdherent(Long id) {
        cpAdherentRepository.deleteById(id);
    }

    @Override
    public CompteAdherent findById(Long id) {
        return cpAdherentRepository.findById(id).orElse(null);
    }


    @Override
    public UserDetails loadUserByUsername(String matricule) throws UsernameNotFoundException {
        CompteAdherent adherent = cpAdherentRepository.findByMatricule(matricule)
                .orElseThrow(() -> new DataNotFoundException("User not found with matricule: " + matricule));
        if (adherent.getRole().equals(Role.ADHERENT)) {
            Adherent adherent1 = adherentRepository.findByMatricule(matricule).orElse(null);
            if(adherent1 == null){
                throw new DataNotFoundException("Adherent not found");
            }
            List<Reclamation> reclamations = reclamationService.findByAdherent(adherent1.getId());
            List<AyantsDroits> ayantsDroits = adherent.getAdherent().getAyantsDroits();
            List<Cotisation> cotisations = cotisationService.getByAdherent(adherent1.getId());
            return new CpAdherentInfoDetails(adherent.getAdherent(), adherent, ayantsDroits,reclamations,cotisations);
        } else {
            List<Reclamation> reclamationsNonLues = reclamationService.reclamationsNonLues();
            return new CpAdherentInfoDetails(null, adherent, null,reclamationsNonLues,null);
        }

    }

    @Override
    public boolean forgetPassword(String matricule) {
        CompteAdherent compteAdherent = cpAdherentRepository.findByMatricule(matricule).orElse(null);
        if(compteAdherent==null){
            throw new DataNotFoundException("Account does not exist");
        }else{
            try{
            String tomporalPassword = PasswordGenerator.generatePassword(8);
            FormatEmailDTO formatEmailDTO = FormatEmailDTO.builder().build();
            formatEmailDTO.setTo(compteAdherent.getEmail());
            formatEmailDTO.setSubject(EmailSubjectDTO.getSubject(1));
            formatEmailDTO.setTomporalPassword(tomporalPassword);
            emailService.sendVerificationEmail(formatEmailDTO,compteAdherent.getNom(), compteAdherent.getPrenom(),
                    compteAdherent.getMatricule(),compteAdherent.getSexe().toString(), EmailSubjectDTO.getType(1),tomporalPassword);
            compteAdherent.setPassword(passwordEncoder.encode(tomporalPassword));
            cpAdherentRepository.save(compteAdherent);
            return true;
            }catch (RuntimeException e){
                return false;
            }
        }
    }

    @Override
    public CompteAdherent updatePassword(Long id, String newPassword) {
        CompteAdherent compteAdherent = cpAdherentRepository.findById(id).orElse(null);
        if(compteAdherent == null){
            throw new DataNotFoundException("Account not found");
        }else{
            compteAdherent.setPassword(passwordEncoder.encode(newPassword));
            return cpAdherentRepository.save(compteAdherent);
        }
    }


}
