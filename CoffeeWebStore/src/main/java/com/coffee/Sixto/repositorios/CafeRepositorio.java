

package com.coffee.Sixto.repositorios;

import com.coffee.Sixto.entidades.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRepositorio extends JpaRepository<Cafe, String>{
    
   /* @Query ("SELECT l FROM Libro l WHERE l.titulo LIKE %?1%")
    public List<Libro> buscarPorTitulo(@Param("titulo") String titulo);
*/
    
    @Query("SELECT o FROM Cafe o WHERE o.origen= :origen")
    public Cafe buscarPorOrigen(@Param("origen")String origen);
    
    
}
