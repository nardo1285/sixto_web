
package com.coffee.Sixto.repositorios;


import com.coffee.Sixto.entidades.Consumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ConsumidorRepositorio extends JpaRepository<Consumidor, String>{
    
    @Query("SELECT o FROM Consumidor o WHERE o.mail= :mail")
    public Consumidor buscarPorEmail(@Param("mail")String mail);
}
