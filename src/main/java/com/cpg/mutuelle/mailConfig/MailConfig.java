package com.cpg.mutuelle.mailConfig;

import com.cpg.mutuelle.entities.dto.FormatEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailConfig {
    @Autowired
    private JavaMailSender javaMailSender;
    public boolean sendVerificationEmail(FormatEmailDTO formatEmailDTO, String firstName, String lastName,
                                         String matricule,
                                         String genderUser, String type, String codeVerification) {


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(formatEmailDTO.getTo());
        message.setSubject(formatEmailDTO.getSubject());
        message.setText(formatEmailDTO.getBody(firstName,lastName,matricule,genderUser,type,codeVerification));

        try {
            javaMailSender.send(message);
            return true;
        } catch (MailException ex) {
            ex.printStackTrace();
            return false;
        }



    }
}