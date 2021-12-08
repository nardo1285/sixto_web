
package com.coffee.Sixto.repositorios;

import com.coffee.Sixto.entidades.PV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PVRepositorio extends JpaRepository<PV, String>{
    
    @Query("SELECT o FROM PV o WHERE o.email= :email")
    public PV buscarPorEmail(@Param("email")String email);
    
}
