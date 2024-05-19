package com.cpg.mutuelle.entities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FormatEmailDTO {
    private String to ;
    private String Subject ;
    private String tomporalPassword;



    public String getBody(String firstName, String lastName, String matricule,
                          String genderUser,
                          String type, String codeVerification) {

        String salutationUser = (genderUser.equalsIgnoreCase("Homme")) ? "Monsieur" : "Madame";

        String cherForUser = (genderUser.equalsIgnoreCase("Homme")) ? "Chèr" : "Chère";

        if (type.equals("AddingAccount")) {
            return "Bonjour " + cherForUser + " " + salutationUser + " " + firstName +" "+lastName+" . Nous espérons que ce message vous trouve bien.\n" +
                    "Suite à votre tentative de création d'un nouveau compte avec ce matricule "+matricule+" , nous vous envoyons un code de vérification pour nos protocoles de sécurité sur notre plateforme.\n " +
                    codeVerification + "\n Cordialement,";
        }else if(type.equals("ForgetPassword")) {
            return "Bonjour " + cherForUser + " " + salutationUser + " " + firstName +" "+lastName+" . Nous espérons que ce message vous trouve bien.\n" +
                    "Suite à votre tentative de réinitialsation de votre mot de passe avec ce matricule "+matricule+" , nous vous envoyons un nouveau mot de passe pour accèder à l'application.\n " +
                    codeVerification + "\n Cordialement,";
        }else{
            return null;
        }

        }
    }

