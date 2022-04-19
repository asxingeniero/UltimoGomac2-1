package Funciones;

import Datos.Ddevoluciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Clock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Fdevoluciones {
    
    private conexion mysql = new conexion(); //Instanciando la clase conexion
    private Connection cn = mysql.conectar();
    private String sSQL = ""; //Sentencia SQL
    public Integer totalRegistros; // Obtener los registros

    public DefaultTableModel mostrar(String buscar) {
       DefaultTableModel modelo;
          //                   1 - 0        2 - 1        3 - 2     4 - 3       5 - 4         6 - 5           7 - 6             8           9          10
        String[] titulos = {"Nro Venta", "Fecha Venta","Usuario","Cliente","Cod detalle","Cod producto","Nombre Producto","Cantidad","Precio","Sub Total","Stock"};

        String[] registros = new String[11];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);
       
//        sSQL = "select * from detalle_venta where nombre_categoria like '%" + buscar + "%' order by cod_categoria desc";
      //  sSQL = "select * from venta where cod_venta like '%" + buscar + "%' ";   
        
           sSQL ="SELECT venta.cod_venta,\n" +  //1
"	venta.fecha_venta,\n" + //2
"	venta.total_venta,\n" +
"	venta.cod_usuarioFK,\n" + //3
"	venta.cod_clienteFK,\n" +
"	persona.nombre_persona,\n" +  //4
"	detalle_venta.cod_detalle,\n" + // 5
"	detalle_venta.cantidad_detalle,\n" + //8
"	detalle_venta.cod_productoFK,\n" + //6
"	detalle_venta.precio_producto,\n" + //9
"	detalle_venta.subtotal,\n" +  //10
"	detalle_venta.subPrecioCompra,\n" +
"	producto.nombre_producto,\n" +  // 7
"       producto.stock_producto\n" +
"       FROM detalle_venta\n" +
"	INNER JOIN venta ON \n" +
"	 detalle_venta.cod_ventaFK = venta.cod_venta \n" +
"	INNER JOIN cliente ON \n" +
"	venta.cod_clienteFK = cliente.cod_cliente \n" +
"	INNER JOIN persona ON \n" +
"	 cliente.cod_cliente = persona.cod_persona \n" +
"	  INNER JOIN producto ON \n" +
"	 detalle_venta.cod_productoFK = producto.cod_producto WHERE CONCAT (venta.cod_venta) LIKE '%"+buscar+"%'  "; // WHERE venta.cod_venta = '"+buscar+"'
                   
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
         

            while (rs.next()) {

                registros[0] = rs.getString("venta.cod_venta");       
                registros[1] = rs.getString("venta.fecha_venta");    
                registros[2] = rs.getString("venta.cod_usuarioFK");  
                registros[3] = rs.getString("persona.nombre_persona");        
                registros[4] = rs.getString("detalle_venta.cod_detalle");
                registros[5] = rs.getString("detalle_venta.cod_productoFK");
                registros[6] = rs.getString("producto.nombre_producto");
                registros[7] = rs.getString("detalle_venta.cantidad_detalle");
                registros[8] = rs.getString("detalle_venta.precio_producto");
                registros[9] = rs.getString("detalle_venta.subtotal");
                registros[10] = rs.getString("producto.stock_producto");
                
                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            
             cn.close();
             
            return modelo; 
            
        } catch (Exception e) {
             Logger.getLogger(Fdevoluciones.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar(Ddevoluciones datos) {
      
        
//         sSQL = "insert into devoluciones (cod_venta,cod_producto,cantidad,obs,fecha_devolucion) values (?,?,?,?,CURDATE())";
          sSQL = "insert into devolucion (cod_venta,cod_producto,nombre_prod,cantidad,obs,fecha_devolucion) values (?,?,?,?,?,CURDATE())";         

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setLong(1, datos.getCod_venta());
            pst.setLong(2, datos.getCod_producto());
            pst.setString(3, datos.getNombre_prod());
            pst.setLong(4, datos.getCantidad());           
            pst.setString(5, datos.getObs());

            int N = pst.executeUpdate();

            if (N != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Logger.getLogger(Fdevoluciones.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        
    }/*CIERRE FUNCION INSERTAR*/
       
 /*  
   public DefaultTableModel mostrar2(String buscar) {
        
        DefaultTableModel modelo;
        
         String[] titulos = {"Nro Venta","Cod producto"};
         
         String[] registros = new String[2];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        
        sSQL ="SELECT venta.cod_venta, devoluciones.cod_producto FROM devoluciones INNER JOIN venta ON devoluciones.cod_venta = venta.cod_venta WHERE CONCAT (venta.cod_venta) LIKE '%"+buscar+"%'  ";
               
        
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
         

            while (rs.next()) {

                registros[0] = rs.getString("venta.cod_venta");  
                registros[1] = rs.getString("devoluciones.cod_producto");
               
               
                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            
             cn.close();
             
            return modelo; 
            
        } catch (Exception e) {
             Logger.getLogger(Fdevoluciones.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e);
            return null;
        } 
    
 */
//    public DefaultTableModel mostrar2(String desde, String hasta) {
    public DefaultTableModel mostrar2(String buscar, String desde, String hasta) {
//    public DefaultTableModel mostrar2(String desde) {
        
        DefaultTableModel modelo;
        
//         String[] titulos = {"Nro Venta","Cod producto","Nombre","Cantidad","Obsercaciones","Fecha devolución"};
         String[] titulos = {"Nro Venta","Cod producto","Nombre","Cantidad","Observaciones","Fecha devolución"};
         
         String[] registros = new String[6];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);
    
    /*    
        sSQL ="SELECT venta.cod_venta, "
                + "devoluciones.cod_producto, "
                + "devoluciones.nombre_prod, "
                + "devoluciones.cantidad, "
                + "devoluciones.obs, "
                + "devoluciones.fecha_devolucion, "
                + "venta.`cod_usuarioFK`, "
                + "venta.`cod_clienteFK` "
                + "FROM devoluciones "
                + "INNER JOIN venta ON devoluciones.cod_venta = venta.cod_venta "
                + "WHERE CONCAT (venta.cod_venta) '%"+buscar+"%'  ";
      */
        sSQL ="SELECT cod_venta,"
                + "cod_producto,"
                + "nombre_prod,"
                + "cantidad,"
                + "obs,"
                + "fecha_devolucion  "
                + "FROM devolucion "
                + "WHERE cod_venta = '"+buscar+"' AND fecha_devolucion BETWEEN '"+desde+"' AND '"+hasta+"' ";
//                + "WHERE cod_venta = '"+desde+"' ";
//                + "WHERE cod_venta = '"+buscar+"' ";
//                + "WHERE cod_venta  BETWEEN '"+desde+"' AND '"+hasta+"' AND '"+buscar+"' ";
                
//                + "devolucion.cod_venta, "
//                + "devolucion.cod_producto, "
//                + "devolucion.nombre_prod, "
//                + "devolucion.cantidad, "
//                + "devolucion.obs, "
//                + "devolucion.fecha_devolucion "            
//                + "FROM devolucion "
//                + "WHERE cod_venta like '%" + buscar + "%'   ";
       
    
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
         

            while (rs.next()) {
                
                 registros[0] = rs.getString("devolucion.cod_venta"); 
                 registros[1] = rs.getString("devolucion.cod_producto");
                 registros[2] = rs.getString("devolucion.nombre_prod");
                 registros[3] = rs.getString("devolucion.cantidad");       
                 registros[4] = rs.getString("devolucion.obs");      
                 registros[5] = rs.getString("devolucion.fecha_devolucion");

//                registros[0] = rs.getString("devolucion.cod_venta");       
//                registros[1] = rs.getString("devolucion.cod_producto");    
//                registros[2] = rs.getString("devolucion.nombre_prod");  
//                registros[3] = rs.getString("devolucion.cantidad");        
//                registros[4] = rs.getString("devolucion.obs");
//                registros[5] = rs.getString("devolucion.fecha_devolucion");
                
                
//                registros[6] = rs.getString("venta.`cod_usuarioFK");
//                registros[7] = rs.getString("venta.`cod_clienteFK");
               
                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            
             cn.close();
             
            return modelo; 
            
        } catch (Exception e) {
             Logger.getLogger(Fdevoluciones.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e);
            return null;
        } 

    }
        

   

    
    
}
