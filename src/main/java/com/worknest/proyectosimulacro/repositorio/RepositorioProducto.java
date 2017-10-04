
package com.worknest.proyectosimulacro.repositorio;

import com.worknest.proyectosimulacro.entidad.Categoria;
import com.worknest.proyectosimulacro.entidad.Producto;
import com.worknest.proyectosimulacro.entidad.ProductoInventario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

/**
 *clase que contiene los procedimientos que se utilizan mediante jpa para 
 * gestionar a "productos"
 */
public interface RepositorioProducto extends JpaRepository<Producto, String> {
    
    /**
     * Metodo para buscar un producto por el codigo de barras
     * @param C_barras, codigo de barras del producto que se va a buscar 
     * @return 
     */   
    Producto findByCodigoBarras(String C_barras);
    
    @Procedure
    void sp_i_producto(String codigoBarras, String nombre,String descripcion, Long cantidad,Float precioCompra, Float precioVenta , Long categoria);

    
    @Query(value = "SELECT * FROM v_inventario", nativeQuery = true)
    List<ProductoInventario> seleccionaproductos();

     
    
}
