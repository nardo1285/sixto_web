
package com.coffee.Sixto.servicios;

import com.coffee.Sixto.entidades.Foto;
import com.coffee.Sixto.excepciones.ErroresSixto;
import com.coffee.Sixto.repositorios.FotoRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {
    
    @Autowired
    private FotoRepositorio fotoRepo;
    
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Foto guardar(MultipartFile archivo) throws ErroresSixto{
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                
                return fotoRepo.save(foto);
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Foto modificar(MultipartFile archivo, String idFoto) throws ErroresSixto{
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                if (idFoto != null) {
                    Optional<Foto> respuesta = fotoRepo.findById(idFoto);
                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }
                    foto.setMime(archivo.getContentType());
                    foto.setNombre(archivo.getName());
                    foto.setContenido(archivo.getBytes());

                    return fotoRepo.save(foto);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
                        
    }
                        
                
                
                

}
