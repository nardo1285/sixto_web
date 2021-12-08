
package com.coffee.Sixto.repositorios;

import com.coffee.Sixto.entidades.Cata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CataRepositorio extends JpaRepository<Cata, String>{
    
}
