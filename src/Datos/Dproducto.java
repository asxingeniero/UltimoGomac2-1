package Datos;


public class Dproducto {
    
    private long cod_producto;                              //1
    private String nombre_producto;                         //2 
    private String descripcion_producto;                    //3
    private String unidad_producto;                         //4
    private long precio_producto;                           //5
    
    private long precio_venta_mayor; // precio al por mayor  //6
     
    private long precio_compra;                              //7
    private long stock_producto;                               //8

    private String ubicacion_bodega;                         //9
    
    private String cod_proveedor;
     
    // Se han creado 9 variables

    public Dproducto() {
    }
                                                                                                                                                  //  6                                      
                   //           1                  2                             3                      4                   5             /* precio venta mayor */          7                      8                   9                                           
    public Dproducto(long cod_producto, String nombre_producto, String descripcion_producto, String unidad_producto, long precio_producto, long precio_venta_mayor, long precio_compra,long stock_producto , String ubicacion_bodega) {
        this.cod_producto = cod_producto;                    //1
        this.nombre_producto = nombre_producto;              //2
        this.descripcion_producto = descripcion_producto;    //3
        this.unidad_producto = unidad_producto;              //4
        this.precio_producto = precio_producto;              //5 
        
        this.precio_venta_mayor = precio_venta_mayor;        //6
        
        this.precio_compra = precio_compra;                //7
        this.stock_producto = stock_producto;                  //8
        this.ubicacion_bodega = ubicacion_bodega;            //9
    }

    
    // # 1
    public long getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(long cod_producto) {
        this.cod_producto = cod_producto;
    }
    
      // # 2
    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }
     // # 3
    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }
    
     // # 4
    public String getUnidad_producto() {
        return unidad_producto;
    }

    public void setUnidad_producto(String unidad_producto) {
        this.unidad_producto = unidad_producto;
    }
     
     // # 5
    public long getPrecio_producto() {
        return precio_producto;
    }
    
     
    public void setPrecio_producto(long precio_producto) {
        this.precio_producto = precio_producto;
    }
    
    // # 6
    // Getter y Setter de precio venta mayor
    public long getPrecio_venta_mayor() {
        return precio_venta_mayor;
    }
    
     
    public void setPrecio_venta_mayor(long precio_venta_mayor) {
        this.precio_venta_mayor = precio_venta_mayor;
    }
    // # 7
    
    public long getPrecio_compra() {
        return precio_compra;
    }
    
     
    public void setPrecio_compra(long precio_compra) {
        this.precio_compra = precio_compra;
    }
    
    // # 8
    
    public long getStock_producto() {
        return stock_producto;
    }
   
     
    public void setStock_producto(long stock_producto) {
        this.stock_producto = stock_producto;
    }
    
    // # 9
    public String getUbicacion_bodega() {
        return ubicacion_bodega;
    }
    
     
    public void setUbicacion_bodega(String ubicacion_bodega) {
        this.ubicacion_bodega = ubicacion_bodega;
    }
    
    /*  Original
    private long cod_producto;
    private String nombre_producto;
    private String descripcion_producto;
    private String unidad_producto;
    private long precio_producto;
    private long stock_producto;
    private long precio_compra;
  
    private String ubicacion_bodega;

    public Dproducto() {
    }

    public Dproducto(long cod_producto, String nombre_producto, String descripcion_producto, String unidad_producto, long precio_producto, long stock_producto, long precio_compra, String ubicacion_bodega) {
        this.cod_producto = cod_producto;
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.unidad_producto = unidad_producto;
        this.precio_producto = precio_producto;
        this.stock_producto = stock_producto;
        this.precio_compra = precio_compra;
        this.ubicacion_bodega = ubicacion_bodega;
    }

    public long getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(long cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public String getUnidad_producto() {
        return unidad_producto;
    }

    public void setUnidad_producto(String unidad_producto) {
        this.unidad_producto = unidad_producto;
    }

    public long getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(long precio_producto) {
        this.precio_producto = precio_producto;
    }

    public long getStock_producto() {
        return stock_producto;
    }

    public void setStock_producto(long stock_producto) {
        this.stock_producto = stock_producto;
    }

    public long getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(long precio_compra) {
        this.precio_compra = precio_compra;
    }

    public String getUbicacion_bodega() {
        return ubicacion_bodega;
    }

    public void setUbicacion_bodega(String ubicacion_bodega) {
        this.ubicacion_bodega = ubicacion_bodega;
    }
  */

    /**
     * @return the cod_proveedor
     */
    public String getCod_proveedor() {
        return cod_proveedor;
    }

    /**
     * @param cod_proveedor the cod_proveedor to set
     */
    public void setCod_proveedor(String cod_proveedor) {
        this.cod_proveedor = cod_proveedor;
    }

    
}
