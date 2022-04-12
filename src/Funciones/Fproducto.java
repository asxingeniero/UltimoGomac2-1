package Funciones;

import static Controlador.FrmProducto.jTabla;
import Datos.Dproducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Fproducto {
    
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    private String sSQL = "";
    public Integer totalRegistros;

    public DefaultTableModel mostrar(String buscar) {  /// trabajando

        DefaultTableModel modelo;

        String[] titulos
                = {"Codigo", "Nombre",
                    "Descripcion", "Unidad",
                    "Precio Venta", "Precio Mayor" , 
                    "Precio Compra", "Stock","Sub Compra","Bodega",
                    "Categoria"};

        String[] registros = new String[11];
        
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

      sSQL=  "SELECT producto.cod_producto,\n" +
"	producto.nombre_producto,\n" +
"	producto.descripcion_producto,\n" +
"	producto.unidad_producto,\n" +
"	producto.precio_producto,\n" +
"	producto.precio_venta_mayor,\n" +
"	producto.precio_compra,\n" +
"	producto.stock_producto,\n" +
"	producto.ubicacion_bodega,\n" +
"	producto.cod_categoriaFK,\n" +             
"	categoria.nombre_categoria,\n" +
"	categoria.cod_categoria,\n" +
"	categoria.descripcion_categoria\n" +
"       FROM producto\n" +
"	INNER JOIN categoria ON \n" +
"	 producto.cod_categoriaFK = categoria.cod_categoria  WHERE CONCAT(producto.cod_producto,producto.nombre_producto) LIKE '%"+buscar+"%'  order by producto.cod_producto desc ";
        

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("producto.cod_producto");
                registros[1] = rs.getString("producto.nombre_producto");
                registros[2] = rs.getString("producto.descripcion_producto");
                registros[3] = rs.getString("producto.unidad_producto");
                registros[4] = rs.getString("producto.precio_producto");                
                registros[5] = rs.getString("producto.precio_venta_mayor");               
                     int PC=  Integer.parseInt(rs.getString("producto.precio_compra"));                                
                     int ST=  Integer.parseInt(rs.getString("producto.stock_producto"));                     
                registros[6] = ""+PC;
                registros[7] = ""+ST;                
                int SUB= PC*ST;                
                registros[8] = ""+SUB;                
                registros[9] = rs.getString("producto.ubicacion_bodega");
                registros[10] = rs.getString("categoria.nombre_categoria");

                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

  public Double Calculo(){
      String Sub_Total="";
      double SUB1=0,SUB2=0;
      
      for(int i=0;i<jTabla.getRowCount();i++)
        {
           Sub_Total=jTabla.getValueAt(i, 8).toString(); 
           SUB1=  Double.parseDouble(Sub_Total);
           SUB2 = SUB2+SUB1;
        }
      
      
      return SUB2;
  }
    
   
    public boolean insertar(Dproducto datos,String nombre) {

        sSQL = "insert into producto (cod_producto, nombre_producto, descripcion_producto"
                + ", unidad_producto, precio_producto, precio_venta_mayor, precio_compra, stock_producto,ubicacion_bodega,cod_proveedor,cod_categoriaFK)"
                + " values (?,?,?,?,?,?,?,?,?,?,"
                + "(select cod_categoria from categoria where nombre_categoria like '%" + nombre + "%'))";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setLong(1, datos.getCod_producto());
            pst.setString(2, datos.getNombre_producto());
            pst.setString(3, datos.getDescripcion_producto());
            pst.setString(4, datos.getUnidad_producto());
            pst.setLong(5, datos.getPrecio_producto());
            
             pst.setLong(6, datos.getPrecio_venta_mayor()); // precio al por mayor
            
            pst.setLong(7, datos.getPrecio_compra());
             pst.setLong(8, datos.getStock_producto());
            
            pst.setString(9, datos.getUbicacion_bodega());
            pst.setString(10, datos.getCod_proveedor());
            
            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {

                return false;
            }

        } catch (Exception e) {
             Logger.getLogger(Fproducto.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }//cierre funcion

    public boolean editar(Dproducto datos, String nombre) {

        sSQL = "update producto set nombre_producto = ? , descripcion_producto = ?  , unidad_producto = ? , precio_producto = ? , precio_venta_mayor = ? , stock_producto = ? ,precio_compra= ? , ubicacion_bodega = ? , cod_categoriaFK =(select cod_categoria from categoria where nombre_categoria like '%" + nombre + "%' limit 1)   where cod_producto =? ";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, datos.getNombre_producto());
            pst.setString(2, datos.getDescripcion_producto());
            pst.setString(3, datos.getUnidad_producto());
            pst.setLong(4, datos.getPrecio_producto());
            
            pst.setLong(5, datos.getPrecio_venta_mayor());
            
            pst.setLong(6, datos.getStock_producto());
            pst.setLong(7, datos.getPrecio_compra());
            pst.setString(8, datos.getUbicacion_bodega());
            pst.setLong(9, datos.getCod_producto());

            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }//cierre funcion

    public boolean eliminar(Dproducto datos) {
        sSQL = "delete from producto where cod_producto = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setLong(1, datos.getCod_producto());
            int N = pst.executeUpdate();

            if (N != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }//cierre funcion
    
    
    
    /**
     * ***** FUNCION STOCK ****
     */
    
    
    public boolean ModificarStockProductos(Dproducto datos) {
        
         sSQL = "update producto set stock_producto = ? where cod_producto = ?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setLong(1, datos.getStock_producto());

            pst.setLong(2, datos.getCod_producto());

            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        
    /*  Original
        sSQL = "update producto set stock_producto = ? where cod_producto = ?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setLong(1, datos.getStock_producto());

            pst.setLong(2, datos.getCod_producto());

            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
      */
    }

    public DefaultTableModel mostrarPorCodigo(String buscar) {

        DefaultTableModel modelo;

        String[] titulos
                = {"Codigo", "Nombre ",
                    "Descripcion", "Unidad  ",
                    "Precio Venta", "Precio Venta Mayor",
                    "Precio Compra", "Stock",  
                    "Bodega", "Categoria"};

        String[] registros = new String[10];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from producto where cod_producto =" + buscar + " order by cod_producto desc";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("cod_producto");
                registros[1] = rs.getString("nombre_producto");
                registros[2] = rs.getString("descripcion_producto");
                registros[3] = rs.getString("unidad_producto");
                registros[4] = rs.getString("precio_producto");
                
                registros[5] = rs.getString("precio_venta_mayor");
                                
                registros[6] = rs.getString("precio_compra");
                registros[7] = rs.getString("stock_producto");
                registros[8] = rs.getString("ubicacion_bodega");
                registros[9] = rs.getString("cod_categoriaFK");

                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public long productoIgual(long codigo) {

        sSQL = "SELECT cod_producto from producto where cod_producto = " + codigo;

        try {
            long cod = 0;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                cod = rs.getLong("cod_producto");
            }

            return cod;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }

    }

    public DefaultTableModel mostrarExportar(String buscar) {

        DefaultTableModel modelo;

        String[] titulos
                = {"Codigo", "Nombre ",
                    "Descripcion", "Unidad  ",
                    "Precio Venta", "Stock", "Precio Compra", "ubicacion bodega"};

        String[] registros = new String[8];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select cod_producto , nombre_producto , descripcion_producto , unidad_producto, precio_producto , stock_producto ,  precio_compra ,ubicacion_bodega from producto where nombre_producto like '%" + buscar + "%' order by cod_producto desc";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("cod_producto");
                registros[1] = rs.getString("nombre_producto");
                registros[2] = rs.getString("descripcion_producto");
                registros[3] = rs.getString("unidad_producto");
                registros[4] = rs.getString("precio_producto");
                registros[5] = rs.getString("stock_producto");
                registros[6] = rs.getString("precio_compra");
                registros[7] = rs.getString("ubicacion_bodega");

                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public ArrayList<String> llenar_combo() {
        ArrayList<String> lista = new ArrayList<String>();
        sSQL = "select nombre_categoria from categoria";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                lista.add(rs.getString("nombre_categoria"));
              
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
    
     public ArrayList<String> llenar_combo2() {
        ArrayList<String> lista2 = new ArrayList<String>();
     //   sSQL = "SELECT persona.nombre_persona persona.cod_persona FROM persona, proveedor WHERE persona.cod_persona = proveedor.cod_proveedor";
        sSQL = "SELECT persona.nombre_persona, persona.cod_persona FROM persona, proveedor WHERE persona.cod_persona = proveedor.cod_proveedor";
     
     try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                lista2.add(rs.getString("cod_persona"));
              
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista2;
    }
           
    
    
    /*  %%%%%% original
        
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    private String sSQL = "";
    public Integer totalRegistros;

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;

        String[] titulos
                = {"Codigo", "Nombre ",
                    "Descripcion", "Unidad  ",
                    "Precio Venta", "Stock",
                    "Precio Compra", "Bodega",
                    "Categoria"};

        String[] registros = new String[9];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select cod_producto , nombre_producto , descripcion_producto , unidad_producto, precio_producto , stock_producto , precio_compra ,ubicacion_bodega,(select nombre_categoria from categoria where  cod_categoria =cod_categoriaFK) as nombre_categoria from producto where nombre_producto like '%" + buscar + "%' order by cod_producto desc";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("cod_producto");
                registros[1] = rs.getString("nombre_producto");
                registros[2] = rs.getString("descripcion_producto");
                registros[3] = rs.getString("unidad_producto");
                registros[4] = rs.getString("precio_producto");
                registros[5] = rs.getString("stock_producto");
                registros[6] = rs.getString("precio_compra");
                registros[7] = rs.getString("ubicacion_bodega");
                registros[8] = rs.getString("nombre_categoria");

                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(Dproducto datos,String nombre) {

        sSQL = "insert into producto (cod_producto , nombre_producto,descripcion_producto"
                + ", unidad_producto , precio_producto,stock_producto,precio_compra,ubicacion_bodega,cod_categoriaFK)"
                + " values (?,?,?,?,?,?,?,?,"
                + "(select cod_categoria from categoria where nombre_categoria like '%" + nombre + "%'))";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setLong(1, datos.getCod_producto());
            pst.setString(2, datos.getNombre_producto());
            pst.setString(3, datos.getDescripcion_producto());
            pst.setString(4, datos.getUnidad_producto());
            pst.setLong(5, datos.getPrecio_producto());
            pst.setLong(6, datos.getStock_producto());
            pst.setLong(7, datos.getPrecio_compra());
            pst.setString(8, datos.getUbicacion_bodega());
            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {

                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }//cierre funcion

    public boolean editar(Dproducto datos, String nombre) {

        sSQL = "update producto set nombre_producto = ? , descripcion_producto = ?  , unidad_producto = ? , precio_producto = ? , stock_producto = ? ,precio_compra= ? , ubicacion_bodega = ? , cod_categoriaFK =(select cod_categoria from categoria where nombre_categoria like '%" + nombre + "%' limit 1)   where cod_producto =? ";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, datos.getNombre_producto());
            pst.setString(2, datos.getDescripcion_producto());
            pst.setString(3, datos.getUnidad_producto());
            pst.setLong(4, datos.getPrecio_producto());
            pst.setLong(5, datos.getStock_producto());
            pst.setLong(6, datos.getPrecio_compra());
            pst.setString(7, datos.getUbicacion_bodega());
            pst.setLong(8, datos.getCod_producto());

            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }//cierre funcion

    public boolean eliminar(Dproducto datos) {
        sSQL = "delete from producto where cod_producto = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setLong(1, datos.getCod_producto());
            int N = pst.executeUpdate();

            if (N != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }//cierre funcion
    
    */
    
    /**
     * ***** FUNCION STOCK ****
     */
    
    /*
    public boolean ModificarStockProductos(Dproducto datos) {

        sSQL = "update producto set stock_producto = ? where cod_producto = ?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setLong(1, datos.getStock_producto());

            pst.setLong(2, datos.getCod_producto());

            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }

    public DefaultTableModel mostrarPorCodigo(String buscar) {

        DefaultTableModel modelo;

        String[] titulos
                = {"Codigo", "Nombre ",
                    "Descripcion", "Unidad  ",
                    "Precio Venta", "Stock", "Precio Compra", "Bodega"};

        String[] registros = new String[8];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from producto where cod_producto =" + buscar + " order by cod_producto desc";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("cod_producto");
                registros[1] = rs.getString("nombre_producto");
                registros[2] = rs.getString("descripcion_producto");
                registros[3] = rs.getString("unidad_producto");
                registros[4] = rs.getString("precio_producto");
                registros[5] = rs.getString("stock_producto");
                registros[6] = rs.getString("precio_compra");
                registros[7] = rs.getString("ubicacion_bodega");

                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public long productoIgual(long codigo) {

        sSQL = "SELECT cod_producto from producto where cod_producto = " + codigo;

        try {
            long cod = 0;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                cod = rs.getLong("cod_producto");
            }

            return cod;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }

    }

    public DefaultTableModel mostrarExportar(String buscar) {

        DefaultTableModel modelo;

        String[] titulos
                = {"Codigo", "Nombre ",
                    "Descripcion", "Unidad  ",
                    "Precio Venta", "Stock", "Precio Compra,ubicacion bodega"};

        String[] registros = new String[8];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select cod_producto , nombre_producto , descripcion_producto , unidad_producto, precio_producto , stock_producto ,  precio_compra ,ubicacion_bodega from producto where nombre_producto like '%" + buscar + "%' order by cod_producto desc";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("cod_producto");
                registros[1] = rs.getString("nombre_producto");
                registros[2] = rs.getString("descripcion_producto");
                registros[3] = rs.getString("unidad_producto");
                registros[4] = rs.getString("precio_producto");
                registros[5] = rs.getString("stock_producto");
                registros[6] = rs.getString("precio_compra");
                registros[7] = rs.getString("ubicacion_bodega");

                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public ArrayList<String> llenar_combo() {
        ArrayList<String> lista = new ArrayList<String>();
        sSQL = "select nombre_categoria from categoria";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                lista.add(rs.getString("nombre_categoria"));
              
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
           */
}
