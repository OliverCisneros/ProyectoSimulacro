/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.entidad;


public class ReporteDiario {


    /**
     * @return the cantidadventa
     */
    public Long getCantidadventa() {
        return cantidadventa;
    }

    /**
     * @param cantidadventa the cantidadventa to set
     */
    public void setCantidadventa(Long cantidadventa) {
        this.cantidadventa = cantidadventa;
    }

    /**
     * @return the idventa
     */
    public Long getIdventa() {
        return idventa;
    }

    /**
     * @param idventa the idventa to set
     */
    public void setIdventa(Long idventa) {
        this.idventa = idventa;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the total_pagar
     */
    public float getTotal_pagar() {
        return total_pagar;
    }

    /**
     * @param total_pagar the total_pagar to set
     */
    public void setTotal_pagar(float total_pagar) {
        this.total_pagar = total_pagar;
    }

    /**
     * @return the ganancia
     */
    public float getGanancia() {
        return ganancia;
    }

    /**
     * @param ganancia the ganancia to set
     */
    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }
    
    


     
     private Long idventa;
     private String fecha;
     private float total_pagar;
     private float ganancia;
     private Long cantidadventa;
     
     
     
}
