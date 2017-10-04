/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * Clase entidad que mapea la tabla ventas
 * de la base de datos
 */
@Entity
@Table(name = "ventas")

public class Ventas implements Serializable {
    
    /**
     *Propiedades de la table que mapean las
     * columnas de la tabla ventas
     */
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idventa")
    private Long idventa;
    
    @Column(name = "fecha")
    private Date fecha;
    
    @Column(name = "total_pagar")
    private float total_pagar;
    
    @Column(name = "ganancia")
    private float ganancia;

    /**
     * 
     * Métodos set y get que asiganan y regresan cada uno de las
     * propiedades de la tabla venta
     */
    
    public Long getIdventa() {
        return idventa;
    }

    public void setIdventa(Long idventa) {
        this.idventa = idventa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(float total_pagar) {
        this.total_pagar = total_pagar;
    }

    public float getGanancia() {
        return ganancia;
    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }

    public Ventas(Date fecha, float total_pagar, float ganancia) {
        this.fecha = fecha;
        this.total_pagar = total_pagar;
        this.ganancia = ganancia;
    }

    public Ventas() {
    }
    
    
    
}
