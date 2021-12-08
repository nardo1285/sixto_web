
package com.coffee.Sixto.servicios;

import com.coffee.Sixto.entidades.Consumidor;
import com.coffee.Sixto.excepciones.ErroresSixto;
import com.coffee.Sixto.repositorios.ConsumidorRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsumidorServicio {

    @Autowired
    private ConsumidorRepositorio consumidorRepo;
    
    //ALTA DEL CONSUMIDOR
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Consumidor registrar(String nombre, String email, String password) throws ErroresSixto{
        
        validar(nombre, email, password);
        Consumidor nuevoConsu = new Consumidor();
        
        nuevoConsu.setNombre(nombre);
        nuevoConsu.setMail(email);
        nuevoConsu.setPassword(password);
        nuevoConsu.setAlta(new Date());
        
        return consumidorRepo.save(nuevoConsu);
        
    }
    
    
    //MODIFICACIÓN DEL CONSUMIDOR
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Consumidor modificar(String id, String nombre, String email, String password) throws ErroresSixto{
        
        validar(nombre, email, password);
        
        Optional<Consumidor> respuesta = consumidorRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Consumidor consumidor = respuesta.get();
            
            consumidor.setNombre(nombre);
            consumidor.setMail(email);
            consumidor.setPassword(password);
            
            return consumidorRepo.save(consumidor);
        }else{
            throw new ErroresSixto("No se encontró el Consumidor solicitado");
        }
    }
    
    
    //BAJA DEL CONSUMIDOR
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void deshabilitar(String id) throws ErroresSixto{
        
        Optional<Consumidor> respuesta = consumidorRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Consumidor consumidor = respuesta.get();
            
            consumidor.setBaja(new Date());
            consumidorRepo.save(consumidor);
            
        }else{
            throw new ErroresSixto("No se encontró el Consumidor solicitado");
        }
    }
    
    
    //ReHABILITACIÓN CONSUMIDOR
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void habilitar(String id) throws ErroresSixto{
        
        Optional<Consumidor> respuesta = consumidorRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Consumidor consumidor = respuesta.get();
            
            consumidor.setBaja(null);
            consumidorRepo.save(consumidor);
            
        }else{
            throw new ErroresSixto("No se encontró el Consumidor solicitado");
        }
    }
    
    
    //VALIDACIONES
    public void validar(String nombre, String email, String password) throws ErroresSixto {
        
        if (nombre == null || nombre.isEmpty()) {
            throw new ErroresSixto("El Nombre no puede ser nulo");
        }
        if (email == null || email.isEmpty()) {
            throw new ErroresSixto("El Email no puede ser nulo");
        }
        if (email.contains("@") == false) {
            throw new ErroresSixto("El Email debe contener el '@'");
        }
        if (password == null || password.isEmpty() || password.length()<4) {
            throw new ErroresSixto("La contraseña no puede ser nula, ni menor de 4 digitos");
        }
        
    }
}
