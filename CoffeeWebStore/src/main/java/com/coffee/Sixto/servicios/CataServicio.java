
package com.coffee.Sixto.servicios;

import com.coffee.Sixto.entidades.Cata;
import com.coffee.Sixto.excepciones.ErroresSixto;
import com.coffee.Sixto.repositorios.CataRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CataServicio {
    
    @Autowired
    private CataRepositorio cataRepo;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Cata cargar(String sabor, String aroma, String cuerpo){
        
        Cata cata = new Cata();
        
        
        cata.setSabor(sabor);
        cata.setAroma(aroma);
        cata.setCuerpo(cuerpo);
        
        return cataRepo.save(cata);
        /*//RECORDATORIO DE COMO SE CARGABA EL ARRAY
            ArrayList<Integer> nota = new ArrayList<>();
            Alumno alu = new Alumno();  //Para cada vuelta, creamos un objeto Alumno
                                        //para cargar sus datos.
            
            //Agrego las notas al Array de notas
            nota.add(nota1);  
            nota.add(nota2);
            nota.add(nota3);
            
            //Agrego los datos al objeto Alumno (el Nombre y el Listado)
            alu.setNombre(nombre);
            alu.setNota(nota);
            
            //Agrego el objeto ya completo a la lista
            alumnoNuevo.add(alu);
        */
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Cata modificar(String idCata, String sabor, String aroma, String cuerpo) throws ErroresSixto{
        
        Optional<Cata> respuesta = cataRepo.findById(idCata);
        if (respuesta.isPresent()) {
            Cata cata = respuesta.get();
            
            cata.setSabor(sabor);
            cata.setAroma(aroma);
            cata.setCuerpo(cuerpo);
                
                return cataRepo.save(cata);
            
        } else {
            throw new ErroresSixto("No existe ese cata en la base de datos. Por favor crearla.");
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void baja(String idCata, String sabor, String aroma, String cuerpo) throws ErroresSixto{
        
        Optional<Cata> respuesta = cataRepo.findById(idCata);
        if (respuesta.isPresent()) {
            Cata cata = respuesta.get();
                
                cataRepo.delete(cata);
            
        } else {
            throw new ErroresSixto("No existe ese cata en la base de datos. Por favor crearla.");
        }
    
    }


}
