
package com.worknest.proyectosimulacro.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

/**
 * 
 * nombre de la tabla y generacion del procedimiento almacenado
 */


@Entity
@Table(name = "categoria")
@NamedStoredProcedureQuery(
		name = "sp_i_categoria", 
		procedureName = "sp_i_categoria", 
		parameters = {
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="nombre_cat", type=String.class),
                      @StoredProcedureParameter(mode = ParameterMode.IN, name="descripcion_cat", type=String.class)
		}
)


public class Categoria implements Serializable{

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * columnas de la tabla categorias de la base de datos
     */
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcategoria") 
    private Long id;
    @Column(name = "nombre_cat")
    private String nombre;
    @Column(name = "descripcion_cat")
    private String descripcion;

    
    

    
}
