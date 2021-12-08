
package com.coffee.Sixto.servicios;

import com.coffee.Sixto.entidades.NotificacionServicio;
import com.coffee.Sixto.entidades.Tostador;
import com.coffee.Sixto.entidades.Zona;
import com.coffee.Sixto.excepciones.ErroresSixto;
import com.coffee.Sixto.repositorios.TostadorRepositorio;
import com.coffee.Sixto.repositorios.ZonaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TostadorServicio implements UserDetailsService{

    @Autowired
    private TostadorRepositorio tostadorRepo;
    @Autowired
    private FotoServicio fotoServi;
    @Autowired
    private NotificacionServicio notificacionServi;
    @Autowired
    private ZonaRepositorio zonaRepo;
    
    
    //ALTA DEL TOSTADOR
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Tostador registrar(MultipartFile archivo, String nombre, String responsableCuenta, 
                            String email, String password, String idZona) throws ErroresSixto{
        
        Zona zona = zonaRepo.getById(idZona);
        Tostador nuevoTosta = new Tostador();
        
        validar(nombre, responsableCuenta, email, password, zona);
        
        nuevoTosta.setFoto(fotoServi.guardar(archivo));
        nuevoTosta.setNombre(nombre);
        nuevoTosta.setResponsableCuenta(responsableCuenta);
        nuevoTosta.setEmail(email);
        nuevoTosta.setZona(zona);
        String encriptada = new BCryptPasswordEncoder().encode(password);
        nuevoTosta.setPassword(encriptada);
        
        nuevoTosta.setAlta(new Date());
        notificacionServi.enviar("Bienvenido al mundo Sixto", "Hola! Ahora tu café, al mundo", email);
        
        return tostadorRepo.save(nuevoTosta);
        
    }
    
    
    //MODIFICACIÓN DEL TOSTADOR
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Tostador modificar(MultipartFile archivo, String id, String nombre, String responsableCuenta, 
                                String email, String password, String idZona) throws ErroresSixto{
        
        Zona zona = zonaRepo.getById(idZona);
        validar(nombre, responsableCuenta, email, password, zona);
        
        Optional<Tostador> respuesta = tostadorRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Tostador tostador = respuesta.get();
            
            String idFoto = null;
            if (tostador.getFoto() != null) {
                idFoto = tostador.getFoto().getId();
            }
            tostador.setFoto(fotoServi.modificar(archivo, idFoto));
            tostador.setNombre(nombre);
            tostador.setResponsableCuenta(responsableCuenta);
            
            String encriptada = new BCryptPasswordEncoder().encode(password);
            tostador.setPassword(encriptada);
            tostador.setEmail(email);
            tostador.setPassword(password);
            
            return tostadorRepo.save(tostador);
        }else{
            throw new ErroresSixto("No se encontró el Tostador solicitado");
        }
    }
    
    
    //BAJA DEL TOSTADOR
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void deshabilitar(String id) throws ErroresSixto{
        
        Optional<Tostador> respuesta = tostadorRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Tostador tostador = respuesta.get();
            
            tostador.setBaja(new Date());
            tostadorRepo.save(tostador);
            
        }else{
            throw new ErroresSixto("No se encontró el Tostador solicitado");
        }
    }
    
    
    //ReHABILITACIÓN TOSTADOR
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void habilitar(String id) throws ErroresSixto{
        
        Optional<Tostador> respuesta = tostadorRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Tostador tostador = respuesta.get();
            
            tostador.setBaja(null);
            tostadorRepo.save(tostador);
            
        }else{
            throw new ErroresSixto("No se encontró el Tostador solicitado");
        }
    }
    
    
    //VALIDACIONES
    public void validar(String nombre, String responsableCuenta, String email, 
                        String password, Zona zona) throws ErroresSixto {
        
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
        if (zona == null) {
            throw new ErroresSixto("No se encontró la zona solicitada");
        }
    }

    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      
        Tostador tostador = tostadorRepo.buscarPorEmail(email);
        if (tostador != null) {
            
            List<GrantedAuthority> permisos = new ArrayList<>();
            
            GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_FOTOS");
            permisos.add(p1);
            GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_aux1");
            permisos.add(p2);
            GrantedAuthority p3 = new SimpleGrantedAuthority("MODULO_aux2");
            permisos.add(p3);
            
            User user = new User(tostador.getEmail(), tostador.getPassword(), permisos);
            return user;
        }else{
            return null;
        }
    }

}
