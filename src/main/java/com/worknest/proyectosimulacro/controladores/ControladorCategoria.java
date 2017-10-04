
package com.worknest.proyectosimulacro.controladores;

import com.worknest.proyectosimulacro.entidad.Categoria;
import com.worknest.proyectosimulacro.repositorio.RepositorioCategoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *Esta clase contiene las solicituded necesarias para maniplar "Categorias"
 * por medio de los URI.
 */
@RestController
@RequestMapping("/categoria")
public class ControladorCategoria {
    /**
     * inyeccion de dependencias y codigo para el funciomamiento de la clase
     * en spring
     */
    
    private final RepositorioCategoria repositoriocategoria;
    public int contadorRegistros = 0;
    @Autowired
    public ControladorCategoria(RepositorioCategoria repositoriocategoria) {
        this.repositoriocategoria = repositoriocategoria;
    }
    
    
    
    
    
    @RequestMapping(method = RequestMethod.POST, path = "/insertar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> agregarElementoJSON(@RequestBody Categoria categoria){
     /**
     * Declaraciones de spring para usar el método post en la URI "insertar" y formatear los datos a JSON que se recojen desde el cuerpo de html
     */
        
       /**
        * En esta condicion, buscamos que si el nombre de la categoria no existe en el momento de agregar una categoria, llamamos al procedimiento almacenado en la base de datos
        * para insertar dicha categoria. En caso contrario, se retorna el estado "NOT_MODIFIED"
        */
        if(repositoriocategoria.findByNombre(categoria.getNombre())==null){
           repositoriocategoria.sp_i_categoria(categoria.getNombre(), categoria.getDescripcion());
           return new ResponseEntity<Categoria>(categoria,HttpStatus.OK); 
        }else{
           return new ResponseEntity<Categoria>(categoria, HttpStatus.NOT_MODIFIED);
        }   
    }
    
    @GetMapping("/leer")
    public List<Categoria> listCategorias(){
        /**
         * Con este método devolvemos todas las categorias
         */
       
   return  repositoriocategoria.seleccionacategorias();
      
}
    
    
}