package com.coffee.Sixto;

import com.coffee.Sixto.servicios.ConsumidorServicio;
import com.coffee.Sixto.servicios.PVServicio;
import com.coffee.Sixto.servicios.TostadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SixtoApplication {

    @Autowired
    private TostadorServicio tostadorServi;
    @Autowired
    private ConsumidorServicio consumidorServi;
    @Autowired
    private PVServicio pvServi;
    
	public static void main(String[] args) {
		SpringApplication.run(SixtoApplication.class, args);
	}
        
    //Si encripto las claves, voy a tener que configurarla en la seguridad del proyecto
    //e incorporar lo siguiente
    
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
            auth
                .userDetailsService(tostadorServi)
                .passwordEncoder(new BCryptPasswordEncoder());
        }

}
