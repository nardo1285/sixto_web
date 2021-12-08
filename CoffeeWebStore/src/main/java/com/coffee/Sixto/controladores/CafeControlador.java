
package com.coffee.Sixto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c")
public class CafeControlador {

    @GetMapping("/carga_cafe")
    public String cargar(){
        
        return "carga_cafe";
    }
}
