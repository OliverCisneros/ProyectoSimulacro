/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historial")
public class Historial implements Serializable{
    
    /**
     * Propiedades de la tabla historial
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idhistorial")
    private Long idhistorial;
    
    @Column(name = "idventa")
    private Long idventa;
    
    @Column(name = "c_barras")
    private String c_barras;
    
    @Column(name = "precio_actual")
    private float precio_actual;
    
    @Column(name = "cantidad")
    private int cantidad;
    
    @Column(name = "total_producto")
    private float total_producto;

    /**
     * 
     * Métodos set y get de las propiedades de la tabla
     * Historial
     */
    public Long getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(Long idhistorial) {
        this.idhistorial = idhistorial;
    }

    public Long getIdventa() {
        return idventa;
    }

    public void setIdventa(Long idventa) {
        this.idventa = idventa;
    }

    public String getC_barras() {
        return c_barras;
    }

    public void setC_barras(String c_barras) {
        this.c_barras = c_barras;
    }


    public float getPrecio_actual() {
        return precio_actual;
    }

    public void setPrecio_actual(float precio_actual) {
        this.precio_actual = precio_actual;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal_producto() {
        return total_producto;
    }

    public void setTotal_producto(float total_producto) {
        this.total_producto = total_producto;
    }

    public Historial(Long idventa, String c_barras, float precio_actual, int cantidad, float total_producto) {
        this.idventa = idventa;
        this.c_barras = c_barras;
        this.precio_actual = precio_actual;
        this.cantidad = cantidad;
        this.total_producto = total_producto;
    }

    public Historial() {
    }
    
    
    
    
}
