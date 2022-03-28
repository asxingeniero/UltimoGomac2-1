package Datos;

import java.sql.Date;


public class Ddevoluciones {
  
    private long cod_venta;
    private long cod_producto;
    private String nombre_prod;
    private long cantidad;
    private String obs;
//    private Date fecha_devolucion;

    public Ddevoluciones() {
    }

//    public Ddevoluciones(int cod_venta, int cod_producto, String nombre_prod, int cantidad, String obs, Date fecha_devolucion) {
//      public Ddevoluciones(int cod_venta, int cod_producto, int cantidad, String obs) { 
    public Ddevoluciones(long cod_venta, long cod_producto, String nombre_prod, long cantidad, String obs) {
    
    this.cod_venta = cod_venta;
        this.cod_producto = cod_producto;
        this.nombre_prod = nombre_prod;
        this.cantidad = cantidad;
        this.obs = obs;
//        this.fecha_devolucion = fecha_devolucion;
    }

    public long getCod_venta() {
        return cod_venta;
    }

    public void setCod_venta(long cod_venta) {
        this.cod_venta = cod_venta;
    }

    public long getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(long cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNombre_prod() {
        return nombre_prod;
    }

    public void setNombre_prod(String nombre_prod) {
        this.nombre_prod = nombre_prod;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

//    public Date getFecha_devolucion() {
//        return fecha_devolucion;
//    }
//
//    public void setFecha_devolucion(Date fecha_devolucion) {
//        this.fecha_devolucion = fecha_devolucion;
//    }
//    
    
    
}
