
package com.coffee.Sixto.servicios;

import com.coffee.Sixto.entidades.Cafe;
import com.coffee.Sixto.entidades.Cata;
import com.coffee.Sixto.enumeracion.Especie;
import com.coffee.Sixto.excepciones.ErroresSixto;
import com.coffee.Sixto.repositorios.CafeRepositorio;
import com.coffee.Sixto.repositorios.CataRepositorio;
import com.coffee.Sixto.repositorios.TostadorRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CafeServicio {

    @Autowired
    private CafeRepositorio cafeRepo;
    
    @Autowired
    private TostadorRepositorio tostadorRepo;
    
    @Autowired
    private CataRepositorio cataRepo;
    
    //ALTA CAFE
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Cafe crearCafe(String idTostador, String idCata, String nombre, Especie especie, 
                            String variedad, String origen, Boolean blend, 
                            String tueste, String beneficio, Integer altitud, 
                            Boolean certificado, Cata cata){
        
        
        Cafe cafeNuevo = new Cafe();
        
        cafeNuevo.setNombre(nombre);
        cafeNuevo.setEspecie(especie);
        cafeNuevo.setVariedad(variedad);
        cafeNuevo.setOrigen(origen);
        cafeNuevo.setBlend(blend);
        cafeNuevo.setTueste(tueste);
        cafeNuevo.setBeneficio(beneficio);
        cafeNuevo.setAltitud(altitud);
        cafeNuevo.setCertificado(certificado);
        //cafeNuevo.setCantidadTostador();
        //cafeNuevo.setCantidadPV();
        //cafeNuevo.setCantidadConsumidor();
        cafeNuevo.setCata(cataRepo.getById(idCata));
        cafeNuevo.setTostador(tostadorRepo.getById(idTostador));//me devuelve el tostador logeado
        //cafeNuevo.setPuntoVenta(puntoVenta);
        //cafeNuevo.setConsumidor(consumidor);
        cafeNuevo.setAlta(new Date());
        
        return cafeRepo.save(cafeNuevo);
    }
    
    
    //MODIFICACIÓN CAFE
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Cafe modificar(String idTostador, String idCafe, String nombre, Especie especie, 
                            String variedad, String origen, Boolean blend, 
                            String tueste, String beneficio, Integer altitud, 
                            Boolean certificado, Cata cata ) throws ErroresSixto{
        
        Optional<Cafe> respuesta = cafeRepo.findById(idCafe);
        if (respuesta.isPresent()) {
            Cafe cafe = respuesta.get();
            if (cafe.getTostador().getId().equals(idTostador)) {
                cafe.setNombre(nombre);
                cafe.setEspecie(especie);
                cafe.setVariedad(variedad);
                cafe.setOrigen(origen);
                cafe.setBlend(blend);
                cafe.setTueste(tueste);
                cafe.setBeneficio(beneficio);
                cafe.setAltitud(altitud);
                cafe.setCertificado(certificado);
                //cafe.setCantidadTostador();
                //cafe.setCantidadPV();
                //cafeNuevo.setCantidadConsumidor();
                cafe.setCata(cata);
                //cafe.setTostador(tostador);
                //cafe.setPuntoVenta(puntoVenta);
                //cafe.setConsumidor(consumidor);
                
                return cafeRepo.save(cafe);
            }else {
                throw new ErroresSixto("No tiene permisos para modificar este café.");
            }
        } else {
            throw new ErroresSixto("No existe ese café en la base de datos. Por favor crearlo.");
        }
    }
    
    //BAJA CAFE
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void baja(String idTostador, String idCafe) throws ErroresSixto{
        
        Optional<Cafe> respuesta = cafeRepo.findById(idCafe);
        if (respuesta.isPresent()) {
            Cafe cafe = respuesta.get();
            if (cafe.getTostador().getId().equals(idTostador)) {
                cafe.setBaja(new Date());
                
                cafeRepo.save(cafe);
            }else {
                throw new ErroresSixto("No tiene permisos para modificar este café.");
            }
        } else {
            throw new ErroresSixto("No existe ese café en la base de datos. Por favor crearlo.");
        }
        
    }
    
    //ReHABILITACIÓN CAFE
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void habilitacion(String idTostador, String idCafe) throws ErroresSixto{
        
        Optional<Cafe> respuesta = cafeRepo.findById(idCafe);
        if (respuesta.isPresent()) {
            Cafe cafe = respuesta.get();
            if (cafe.getTostador().getId().equals(idTostador)) {
                cafe.setBaja(null);
                
                cafeRepo.save(cafe);
            }else {
                throw new ErroresSixto("No tiene permisos para modificar este café.");
            }
        } else {
            throw new ErroresSixto("No existe ese café en la base de datos. Por favor crearlo.");
        }
        
    }
}
