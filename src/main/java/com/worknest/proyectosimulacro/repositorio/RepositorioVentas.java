/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;


public interface RepositorioVentas extends JpaRepository<Ventas, Long>{
    
    @Procedure
    String reportediario(String date);
    
    @Procedure
    String reportemensual1(String date);  //Todo menos la cantidad
    
    @Procedure
    String reportemensual2(String date);
}