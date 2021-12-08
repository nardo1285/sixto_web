
package com.coffee.Sixto.servicios;

import com.coffee.Sixto.entidades.Zona;
import com.coffee.Sixto.excepciones.ErroresSixto;
import com.coffee.Sixto.repositorios.ZonaRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
class ZonaServicio {
    
    @Autowired
    private ZonaRepositorio zonaRepo;

    //AGREGAR ZONA
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Zona cargar(String nombre){
        
        Zona zona = new Zona();
        
        zona.setNombre(nombre);
        
        return zonaRepo.save(zona);
        
    }
    
    //MODIFICAR ZONA
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Zona modificar(String id, String nombre) throws ErroresSixto{
        Optional<Zona> respuesta = zonaRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Zona zona = respuesta.get();
            
            zona.setNombre(nombre);
            return zonaRepo.save(zona);
            
        }else{
            throw new ErroresSixto("No se encontró la Zona solicitada");
        }
    }
    
    //ELIMINAR ZONA
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void eliminar(String id, String nombre) throws ErroresSixto{
        Optional<Zona> respuesta = zonaRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Zona zona = respuesta.get();
            
            zonaRepo.delete(zona);
            
        }else{
            throw new ErroresSixto("No se encontró la Zona solicitada");
        }
    }
            
}
