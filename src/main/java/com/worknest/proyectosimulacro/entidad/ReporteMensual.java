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
public class ReporteMensual {

    /**
     * @return the cantdia
     */
    public float getCantdia() {
        return cantdia;
    }

    /**
     * @param cantdia the cantdia to set
     */
    public void setCantdia(float cantdia) {
        this.cantdia = cantdia;
    }

    /**
     * @return the dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * @return the totaldia
     */
    public float getTotaldia() {
        return totaldia;
    }

    /**
     * @param totaldia the totaldia to set
     */
    public void setTotaldia(float totaldia) {
        this.totaldia = totaldia;
    }

    /**
     * @return the gananaciadia
     */
    public float getGananciadia() {
        return gananciadia;
    }

    /**
     * @param gananaciadia the gananaciadia to set
     */
    public void setGananciadia(float ganaaciadia) {
        this.gananciadia = ganaaciadia;
    }
    
    private String dia;
    private float totaldia;
    private float gananciadia;
    private float cantdia;
}
