package com.cpg.mutuelle.services;

import com.cpg.mutuelle.entities.Cotisation;
import com.cpg.mutuelle.entities.dto.CotisationDTO;
import com.cpg.mutuelle.entities.enumerations.TypeCotisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@Service
public class TraitementCotisationImpl implements ITraitementCotisation {
    @Autowired
    private ICotisationService cotisationService;
    @Autowired
    private IAdherentService adherentService;


    @Override
    public CotisationDTO getCotisation(Long idAdherent) {
        List<Cotisation> cotisations = cotisationService.getByAdherent(idAdherent);
        Cotisation cotisation = cotisationService.getRecentById(idAdherent);
        CotisationDTO cotisationDTO = CotisationDTO.builder().build();
        int intervalle = 0;

        if (cotisations.isEmpty()) {
            intervalle = calculerIntervalleMois(getDateRetraite(idAdherent));
            cotisationDTO.setMontantcnss(getMontantCNSS(idAdherent));

            if (intervalle <= 12) {
                cotisationDTO.setTypeCotisation(getMontantCNSS(idAdherent) == 0 ? TypeCotisation.AVANCE : TypeCotisation.COTISATION);
                cotisationDTO.setDateDebutComptage(getDateRetraite(idAdherent));
                if (cotisationDTO.getTypeCotisation().equals(TypeCotisation.AVANCE)) {
                    cotisationDTO.setIndice("6");
                }

            } else {
                cotisationDTO.setTypeCotisation(TypeCotisation.COTISATION);
                cotisationDTO.setDateDebutComptage(LocalDate.now().withDayOfMonth(1));
                cotisationDTO.setIndice("messagePenalite");
            }
        } else {

            intervalle = calculerIntervalleMois(cotisation.getCotisation().equals(TypeCotisation.AVANCE) ? getDateRetraite(idAdherent) : cotisation.getDateFin());
            cotisationDTO.setMontantcnss(getMontantCNSS(idAdherent));

            if (cotisation.getCotisation().equals(TypeCotisation.AVANCE) || intervalle <= 12) {
                cotisationDTO.setTypeCotisation(cotisation.getCotisation().equals(TypeCotisation.AVANCE) ? TypeCotisation.REGULARISATION : TypeCotisation.COTISATION);

                if(cotisationDTO.getTypeCotisation().equals(TypeCotisation.REGULARISATION)){
                    cotisationDTO.setIndice("6");
                }
                if (cotisation.getCotisation().equals(TypeCotisation.AVANCE)){
                    cotisationDTO.setDateDebutComptage(getDateRetraite(idAdherent));
                }else{
                    cotisationDTO.setDateDebutComptage(cotisation.getDateFin());
                }

            } else {
                cotisationDTO.setTypeCotisation(TypeCotisation.COTISATION);
                cotisationDTO.setDateDebutComptage(LocalDate.now().withDayOfMonth(1));
                cotisationDTO.setIndice("messagePenalite");
            }
        }
        return cotisationDTO;
    }


    @Override
    public String getMontantCotisation(Long idAdherent, int nbr_period) {
        List<Cotisation> cotisations = cotisationService.getByAdherent(idAdherent);
        Cotisation cotisation = cotisationService.getRecentById(idAdherent);
        int intervalle;
        double montantCotisation;

        if (cotisations.isEmpty()) {
            intervalle = calculerIntervalleMois(getDateRetraite(idAdherent));
            if (intervalle <= 12) {
                montantCotisation = (getMontantCNSS(idAdherent) == 0) ?
                        (getAssiette(idAdherent) * 2 * nbr_period) / 100 :
                        (getMontantCNSS(idAdherent) * 2 * nbr_period) / 100;
            } else {
                montantCotisation = (getMontantCNSS(idAdherent) * 2 * (nbr_period + 12)) / 100;
            }
        } else if (cotisation.getCotisation().equals(TypeCotisation.AVANCE)) {
            montantCotisation = ((getMontantCNSS(idAdherent) * 2 * nbr_period) / 100 - cotisation.getMontant());
        } else {
            intervalle = calculerIntervalleMois(cotisation.getDateFin());
            montantCotisation = (intervalle <= 12) ?
                    (getMontantCNSS(idAdherent) * 2 * nbr_period) / 100 :
                    (getMontantCNSS(idAdherent) * 2 * (nbr_period + 12)) / 100;
        }
        return String.format("%.3f DT", montantCotisation);


    }
    public double getMontantCNSS(Long idAdherent){
        return adherentService.findById(idAdherent)
                .getSalaireCNSS();
    }
    public LocalDate getDateRetraite(Long idAdherent){
        return adherentService.findById(idAdherent)
                .getDateDepartRetraite();
    }
    public double getAssiette(Long idAdherent){
        return adherentService.findById(idAdherent)
                .getAssiette();
    }
    public static int calculerIntervalleMois(LocalDate dateFin) {
        LocalDate now = LocalDate.now();
        LocalDate dateDebut = dateFin.plusMonths(1);
        int intervalle = 0;

        while (dateDebut.isBefore(now) || dateDebut.equals(now)) {
            intervalle++;
            dateDebut = dateDebut.plusMonths(1);
        }
        return intervalle;
    }

}