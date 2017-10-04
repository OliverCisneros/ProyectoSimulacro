/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Producto;
import com.worknest.proyectosimulacro.entidad.ProductoInventario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author WorkNest8
 */
public interface RepositorioProductoVista extends JpaRepository<ProductoInventario, String>{
    
        
    @Query(value = "SELECT * FROM v_inventario", nativeQuery = true)
    List<ProductoInventario> seleccionaproductos();
    
    
}
