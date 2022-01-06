
package com.coffee.Sixto.controladores;

import com.coffee.Sixto.entidades.Zona;
import com.coffee.Sixto.excepciones.ErroresSixto;
import com.coffee.Sixto.repositorios.ZonaRepositorio;
import com.coffee.Sixto.servicios.TostadorServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/t")
public class TostadorControlador {
    
    @Autowired 
    private TostadorServicio tostadorServi;
    @Autowired
    private ZonaRepositorio zonaRepo;
    
    
    @GetMapping("/tostadores")
    public String inicio(){
        
        return "tostadores";
    }
    
    
    @GetMapping("/registro-tostador")
    public String registro(ModelMap modelo){
    
        List<Zona> zonas = zonaRepo.findAll();
        modelo.put("zonas", zonas);
        
        return "registro-tostador";
    }

    
    @GetMapping("/login-tostador")
    public String login(HttpSession session, Authentication tostador, ModelMap modelo, @RequestParam(required = false) String error) {
        try {
            if (tostador.getName() != null) {
                    return "redirect:/";
            } else {
                if (error != null && !error.isEmpty()) {
                        modelo.addAttribute("error", "La dirección de mail o la contraseña que ingresó son incorrectas.");
                }
                return "login-tostador";
            }
        } catch (Exception e) {
            if (error != null && !error.isEmpty()) {
                    modelo.addAttribute("error", "La dirección de mail o la contraseña que ingresó son incorrectas.");
            }
            return "login-tostador";
        }
    }
			

    @PostMapping("/registro-tostador")
    public String login(ModelMap modelo, MultipartFile archivo, @RequestParam String nombre, @RequestParam String responsableCuenta, 
                        @RequestParam String mail, @RequestParam String password1, @RequestParam String password2, @RequestParam String idZona) throws ErroresSixto{
        try {
            tostadorServi.registrar(archivo, nombre, responsableCuenta, mail, password1, idZona);
            modelo.put("exito", "¡Registro Exitoso!");
            
        } catch (ErroresSixto ex) {
            
            List<Zona> zonas = zonaRepo.findAll();
            modelo.put("zonas", zonas);
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("responsableCuenta", responsableCuenta);
            modelo.put("mail", mail);
            modelo.put("password1", password1);
            modelo.put("password2", password2);
            
            return "registro-tostador";
            
        }
        
        return "exito_login_tostador";
    }
}
