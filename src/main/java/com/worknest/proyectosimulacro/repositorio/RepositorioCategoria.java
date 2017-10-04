
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

/**
 *clase que contiene los procedimientos que se utilizan mediante jpa para 
 * gestionar a "categorias"
 */

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long>{
     
    Categoria findByNombre(String nombre);
    
    @Procedure
    void sp_i_categoria(String nombre, String descripcion);
    
    
    @Query(value = "SELECT * FROM categoria", nativeQuery = true)
    List<Categoria> seleccionacategorias();
      
}

