/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worknest.proyectosimulacro.controladores;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.worknest.proyectosimulacro.entidad.Fecha;
import com.worknest.proyectosimulacro.entidad.Historial;
import com.worknest.proyectosimulacro.entidad.Producto;
import com.worknest.proyectosimulacro.entidad.ProductoVenta;
import com.worknest.proyectosimulacro.entidad.ReporteDiario;
import com.worknest.proyectosimulacro.entidad.ReporteMensual;
import com.worknest.proyectosimulacro.entidad.Totales;
import com.worknest.proyectosimulacro.entidad.Ventas;
import com.worknest.proyectosimulacro.repositorio.RepositorioHistorial;
import com.worknest.proyectosimulacro.repositorio.RepositorioProducto;
import com.worknest.proyectosimulacro.repositorio.RepositorioVentas;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author WorkNest
 */
@RestController 
@RequestMapping("/venta")
public class ControladorVentas {
    /**
     * Inyeccion de dependencias
     */
    @Autowired
    private RepositorioVentas repositorioVentas;
    @Autowired
    private RepositorioProducto repositorioproducto;
    @Autowired
    private RepositorioHistorial repositorioHistorial;

    
    
    /**
     * Constructor de la clase ControladorVentas
     * @param repositorioVentas 
     */
    @Autowired
    public ControladorVentas(RepositorioVentas repositorioVentas) {
        this.repositorioVentas = repositorioVentas;
    }
    
    /**
     * Método que registra la venta
     * @param producto el cual es una lista de los productos seleccionados para una compra
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST, path = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ventas> agregarVenta(@RequestBody List<ProductoVenta> producto){
        
       /**
        * 
        * variable para sumar el total
        */
        float sumaDeCompra = 0 , sumaDeVenta = 0 , ganancia = 0;
        
        /**
         * Ciclo que recorre la lista de productos seleccionados para una compra
         */
        for(ProductoVenta productoVenta : producto){

            Producto productoABuscar = repositorioproducto.findByCodigoBarras(productoVenta.getC_barras()); //Se busca el producto en base al codigo de barras
            sumaDeCompra += productoABuscar.getPrecioCompra() * productoVenta.getCantidad(); //Se calcula la suma del precio de compra de todos los productos
            sumaDeVenta += productoABuscar.getPrecioVenta() * productoVenta.getCantidad(); //Se calcula la suma del precio de venta de todos los productos
            
        }
        //Se calcula la ganancia neta de la venta
        ganancia = sumaDeVenta - sumaDeCompra;
        
        //Se crea la venta 
        Ventas venta = new Ventas(new Date(),sumaDeVenta,ganancia);
        //Se registra la venta en la base de datos
        repositorioVentas.save(venta);
        //Se restaura el valor de sumaVenta para asignarla a cada producto
        sumaDeVenta =0;
        /**
         * Ciclo que guarda los productos comprados en el historial
         */
        for (ProductoVenta productoVenta : producto) {
            
            Producto productoABuscar = repositorioproducto.findByCodigoBarras(productoVenta.getC_barras()); //Se busca el producto en base al codigo de barras
            sumaDeVenta = productoABuscar.getPrecioVenta() * productoVenta.getCantidad(); //Se calcula el total de la venta de un producto
            Historial historial = new Historial(venta.getIdventa(), productoVenta.getC_barras(), productoABuscar.getPrecioVenta(), productoVenta.getCantidad(), sumaDeVenta); //Se crea un nuevo historial del producto comprado
            repositorioHistorial.save(historial); //Se guarda el historial en la base de datos
            productoABuscar.setCantidad(productoABuscar.getCantidad()-productoVenta.getCantidad()); //Se resta la cantidad de productos comprados
            repositorioproducto.save(productoABuscar);//Se actualiza la base datos con los productos restantes
        }
       
        return new ResponseEntity<>(HttpStatus.OK); 

    } 
    /**
     * Metodo que obtiene los reportes diarios
     * @param fecha, parametro que recibe la fecha para obtener el dia del reporte deseado
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST, path = "/reporteDiario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generarReporteDiario(@RequestBody Fecha fecha){
        
         float gananciaTotal = 0;
        
         String reportesDevueltos = repositorioVentas.reportediario(fecha.getFecha());  //Obtenemos la cadena que regresa el procedimiento almacenado
         
         if(reportesDevueltos==null){
           return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);  //Si la cadena esta vacia, significa que no hay ventas en ese dia
        }else{
           
         
         Type tipoReporte = new TypeToken<List<ReporteDiario>>() {}.getType();  //Obtenemos el tipo del objeto reporte, sera necesario para utilizar el Gson mas adelante
         
         Gson gson = new Gson();                                            //Instanciamos la clase Gson (Api auxiliar para generar JSons)
         
         List<ReporteDiario> reportesDeJson = gson.fromJson(reportesDevueltos,tipoReporte);  //Mapeamos la lista de Reportes en base a la cadena devuelta por el proc. almacenado
         
      
        
        for(ReporteDiario reporte : reportesDeJson){                                  //Por cada objeto reporte en la Lista reportesDeJson...
            gananciaTotal += reporte.getGanancia();                             //sumatoria de cada ganancia en cada reporte
        }
        
        Totales mapeoDeTotales = new Totales(reportesDeJson.size(),gananciaTotal);      //Instancia de la clase totales en donde seteamos los totales obtenidos
        
        UnionProductosYTotales uniendoJSons = new UnionProductosYTotales(reportesDeJson,mapeoDeTotales);      //Unimos los reportesDeJson y el mapeoDeTotales en una misma instancia
        
        String cadenaGeneradaPorJson = gson.toJson(uniendoJSons);               //Generamos la cadena JSon en base al objeto de union anterior
       
            return new ResponseEntity<>(cadenaGeneradaPorJson, HttpStatus.OK);  //Regresamos el JSon generado
         }
        
    }
        /**
     * Metodo que obtiene los reportes mensuales
     * @param fecha, parametro que recibe la fecha para obtener el mes del reporte deseado
     * @return 
     */
    
    @RequestMapping(method = RequestMethod.POST, path = "/reporteMensual", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generarReporteMensual(@RequestBody Fecha fecha){
         
        
         float gananciaTotal = 0;  //Sumatoria de la ganancia total del mes
        
         String reportesDevueltos1 = repositorioVentas.reportemensual1(fecha.getFecha());  //Obtenemos la cadena que regresa el procedimiento almacenado 1, este devuelve (fecha, ganancia, total del dia)
         String reportesDevueltos2 = repositorioVentas.reportemensual2(fecha.getFecha());  //Obtenemos la cadena que regresa el procedimiento almacenado 2, este devuelve la cantidad de productos del dia.
         
         if(reportesDevueltos1==null){
           return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);  //Si la cadena esta vacia, significa que no hay ventas en ese mes
        }else{
         
        Type tipoReporte = new TypeToken<List<ReporteMensual>>() {}.getType();  //Obtenemos el tipo del objeto reporte, sera necesario para utilizar el Gson mas adelante
        
         
        Gson gson = new Gson();                                            //Instanciamos la clase Gson (Api auxiliar para generar JSons)
         
        List<ReporteMensual> reportesDeJson = gson.fromJson(reportesDevueltos1,tipoReporte);  //Mapeamos la lista de Reportes en base a la cadena devuelta por el proc. almacenado
        List<ReporteMensual> reportesDeJson2 = gson.fromJson(reportesDevueltos2,tipoReporte);  //Mapeamos la lista de Reportes en base a la cadena devuelta por el proc. almacenado
         
        
        for(int i = 0; i < reportesDeJson.size(); i++){
            gananciaTotal += reportesDeJson.get(i).getGananciadia();                          //sumatoria de cada ganancia en cada reporte
            reportesDeJson.get(i).setCantdia(reportesDeJson2.get(i).getCantdia());            //Une los resultados del proc almacenado 2 con los del proc almacenado 1
        }
         
        Totales mapeoDeTotales = new Totales(reportesDeJson.size(),gananciaTotal);      //Instancia de la clase totales en donde seteamos los totales obtenidos
        
        UnionProductosYTotales uniendoJSons = new UnionProductosYTotales(reportesDeJson,mapeoDeTotales);      //Unimos los reportesDeJson y el mapeoDeTotales en una misma instancia
        
        String cadenaGeneradaPorJson = gson.toJson(uniendoJSons);               //Generamos la cadena JSon en base al objeto de union anterior
       
            return new ResponseEntity<>(cadenaGeneradaPorJson, HttpStatus.OK);  //Regresamos el JSon generado
         }
    }
    
    
    /**
     * Clase que sirve para encapsular una Lista de genericos (Reporte mensual y diario) y un objeto de totales
     * @param <T> Recibe lista de genericos y un objeto Total
     */
    class UnionProductosYTotales <T>{
        private List<T> reporteJson;                                            
        private  Totales mapeoDeTotales;

        public UnionProductosYTotales(List<T> reporteJson, Totales mapeoDeTotales) {
            this.reporteJson = reporteJson;
            this.mapeoDeTotales = mapeoDeTotales;
        }
        
        
        
    }
    
    
     
    
}
