
package com.coffee.Sixto.servicios;

import com.coffee.Sixto.entidades.PV;
import com.coffee.Sixto.excepciones.ErroresSixto;
import com.coffee.Sixto.repositorios.PVRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PVServicio {

    @Autowired
    private PVRepositorio pvRepo;
    @Autowired
    private FotoServicio fotoServi;
    
    //ALTA DEL PV
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public PV registrar(MultipartFile archivo, String nombre, String responsableCuenta, String email, String password) throws ErroresSixto{
        
        validar(nombre, responsableCuenta, email, password);
        PV nuevoPV = new PV();
        
        nuevoPV.setNombre(nombre);
        nuevoPV.setNombre(responsableCuenta);
        nuevoPV.setEmail(email);
        nuevoPV.setPassword(password);
        nuevoPV.setAlta(new Date());
        nuevoPV.setFoto(fotoServi.guardar(archivo));
        
        return pvRepo.save(nuevoPV);
        
    }
    
    
    //MODIFICACIÓN DEL PV
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public PV modificar(MultipartFile archivo, String id, String nombre, String responsableCuenta, String email, String password) throws ErroresSixto{
        
        validar(nombre, responsableCuenta, email, password);
        
        Optional<PV> respuesta = pvRepo.findById(id);
        
        if (respuesta.isPresent()) {
            PV pv = respuesta.get();
            
            pv.setNombre(nombre);
            pv.setNombre(responsableCuenta);
            pv.setEmail(email);
            pv.setPassword(password);
            
            String idFoto = null;
            if (pv.getFoto() != null) {
                idFoto = pv.getFoto().getId();
            }
            pv.setFoto(fotoServi.modificar(archivo, idFoto));
            
            return pvRepo.save(pv);
        }else{
            throw new ErroresSixto("No se encontró el PV solicitado");
        }
    }
    
    
    //BAJA DEL PV
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void deshabilitar(String id) throws ErroresSixto{
        
        Optional<PV> respuesta = pvRepo.findById(id);
        
        if (respuesta.isPresent()) {
            PV pv = respuesta.get();
            
            pv.setBaja(new Date());
            pvRepo.save(pv);
            
        }else{
            throw new ErroresSixto("No se encontró el PV solicitado");
        }
    }
    
    
    //ReHABILITACIÓN PV
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void habilitar(String id) throws ErroresSixto{
        
        Optional<PV> respuesta = pvRepo.findById(id);
        
        if (respuesta.isPresent()) {
            PV pv = respuesta.get();
            
            pv.setBaja(null);
            pvRepo.save(pv);
            
        }else{
            throw new ErroresSixto("No se encontró el PV solicitado");
        }
    }
    
    
    //VALIDACIONES
    public void validar(String nombre, String responsableCuenta, String email, String password) throws ErroresSixto {
        
        if (nombre == null || nombre.isEmpty()) {
            throw new ErroresSixto("El Nombre no puede ser nulo");
        }
        if (responsableCuenta == null || responsableCuenta.isEmpty()) {
            throw new ErroresSixto("El Responsable de Cuenta no puede ser nulo");
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
