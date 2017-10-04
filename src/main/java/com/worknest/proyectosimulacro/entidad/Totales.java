/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.entidad;

/**
 *
 * @author WorkNest5
 */
public class Totales {

    /**
     * @return the cantidadTotal
     */
    public int getCantidadTotal() {
        return cantidadTotal;
    }

    /**
     * @param cantidadTotal the cantidadTotal to set
     */
    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    /**
     * @return the gananciaTotal
     */
    public float getGananciaTotal() {
        return gananciaTotal;
    }

    /**
     * @param gananciaTotal the gananciaTotal to set
     */
    public void setGananciaTotal(float gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }
    
    private int cantidadTotal;
    private float gananciaTotal;

    public Totales() {
    }

    public Totales(int cantidadTotal, float gananciaTotal) {
        this.cantidadTotal = cantidadTotal;
        this.gananciaTotal = gananciaTotal;
    }
    
    
    
}
