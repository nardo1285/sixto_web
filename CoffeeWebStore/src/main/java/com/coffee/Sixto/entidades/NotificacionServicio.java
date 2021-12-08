
package com.coffee.Sixto.entidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class NotificacionServicio {
    
    @Autowired
    private JavaMailSender mailSender;
    
    
    @Async
    public void enviar(String cuerpo, String titulo, String mail){
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(mail);
        mensaje.setFrom("noreply@sixto.com");
        mensaje.setText(cuerpo);
        mensaje.setSubject(titulo);
        
        mailSender.send(mensaje);
        
    }    
    
    
}
