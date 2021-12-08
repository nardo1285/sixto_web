
package com.coffee.Sixto.repositorios;

import com.coffee.Sixto.entidades.Tostador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TostadorRepositorio extends JpaRepository<Tostador, String>{
    
    @Query("SELECT o FROM Tostador o WHERE o.email= :email")
    public Tostador buscarPorEmail(@Param("email")String email);
    
}
