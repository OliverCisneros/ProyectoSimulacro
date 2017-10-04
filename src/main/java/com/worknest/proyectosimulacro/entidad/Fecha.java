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
public class Fecha {

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
        
        private String fecha;
        
        public Fecha(){
            
        }
        
        public Fecha(String fecha){
            this.fecha = fecha;
        }
    }
