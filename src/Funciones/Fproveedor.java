package Funciones;

//import Datos.Dcliente;
import Datos.Dproveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Fproveedor {

    private conexion mysql = new conexion(); //Instanciando la clase conexion
    private Connection cn = mysql.conectar();
    private String sSQL = ""; //Sentencia SQL
    private String sSQL2 = "";
    public Integer totalRegistros; // Obtener los registros

    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;

        String[] titulos
                = {"Codigo", "Nombre",
                    "Direccion", "Telefono",
                    "Email", "Cedula"};

        String[] registros = new String[6];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.cod_persona , p.nombre_persona,p.direccion,p.telefono"
                + " , p.email, pr.rut_proveedor from "
                + " persona p inner join proveedor pr on p.cod_persona = pr.cod_proveedor "
                + " where nombre_persona like '%" + buscar + "%' order by cod_persona desc";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("cod_persona");
                registros[1] = rs.getString("nombre_persona");
                registros[2] = rs.getString("direccion");
                registros[3] = rs.getString("telefono");
                registros[4] = rs.getString("email");
                registros[5] = rs.getString("rut_proveedor");

                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    /**
     ****Cierre Funcion Mostrar******
     */
    public boolean insertar(Dproveedor datos) {

        sSQL = "insert into persona (nombre_persona,direccion,telefono,email)"
                + "values (?,?,?,?)";
       

        sSQL2 = "insert into proveedor(cod_proveedor,rut_proveedor)"
                + " values ((select cod_persona from persona order by cod_persona desc limit 1)"
                + " ,?)";
           
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, datos.getNombre_persona());
            pst.setString(2, datos.getDireccion());
            pst.setString(3, datos.getTelefono());
            pst.setString(4, datos.getEmail());
          
            pst2.setString(1, datos.getRut_proveedor());
       
            int N = pst.executeUpdate();
            if (N != 0) {

                int N2 = pst2.executeUpdate();

                if (N2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }/*CIERRE FUNCION INSERTAR*/


    public boolean editar(Dproveedor datos) {

        sSQL = "update persona set nombre_persona = ? ,direccion = ? ,"
                + "telefono = ?,email = ? where cod_persona = ? ";

        sSQL2 = "update proveedor set rut_proveedor = ? where cod_proveedor = ? ";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, datos.getNombre_persona());
            pst.setString(2, datos.getDireccion());
            pst.setString(3, datos.getTelefono());
            pst.setString(4, datos.getEmail());
            pst.setInt(5, datos.getCod_persona());

            pst2.setString(1, datos.getRut_proveedor());
            pst2.setInt(2, datos.getCod_persona());

            int N = pst.executeUpdate();
            if (N != 0) {
                int N2 = pst2.executeUpdate();

                if (N2 != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }/*CIERRE FUNCION EDITAR*/


    public boolean eliminar(Dproveedor datos) {

        sSQL = "delete from persona where cod_persona = ? ";
        sSQL2 = "delete from proveedor where cod_proveedor = ?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setInt(1, datos.getCod_persona());
            pst2.setInt(1, datos.getCod_proveedor());

            int N = pst.executeUpdate();

            if (N != 0) {

                int N2 = pst2.executeUpdate();
                if (N2 != 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }/*CIERRE FUNCION ELIMINAR*/
    
    
    
    
    
    
    
    
    
    
    /****CLIENTE GENERAL***/
    
    /*
       public int PrimerCliente() {
        
        sSQL= "SELECT COUNT(cod_cliente) as cantidad FROM cliente ";
        try {
             int cantidad = 0;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                cantidad = rs.getInt("cantidad");
            }
            return cantidad;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
           */
//cierre funcion */
       
       
      /*
       public boolean insertarPrimercLiente() {

        sSQL = "insert into persona (nombre_persona,direccion,telefono,email)"
                + "values ('Cliente General','0','0','0')";

        sSQL2 = "insert into cliente (cod_cliente,rut_cliente)"
                + " values ((select cod_persona from persona order by cod_persona desc limit 1)"
                + " ,'0')";
       
         
        try{
 
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.executeUpdate();
            pst2.executeUpdate();
        }catch(Exception e){
            
            
        }
      return true;

    }
       */
/*CIERRE FUNCION INSERTAR*/
       
    

}
