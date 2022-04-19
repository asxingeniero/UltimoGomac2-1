package Funciones;

import Datos.Dgastos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;


public class Fgastos {
    
    private conexion mysql = new conexion(); //Instanciando la clase conexion
    private Connection cn = mysql.conectar();
    private String sSQL = ""; //Sentencia SQL
    public Integer totalRegistros; // Obtener los registros
    
    
      public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;

        String[] titulos
                = {"Codigo", "Descripción",
                    "Valor", "Observación    ",
                    };

        String[] registros = new String[4];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

//        sSQL = "select * from datos_empresa where nombre_empresa like '%" + buscar + "%' order by cod_empresa desc";
         sSQL = "select * from gastos where descripcion like '%" + buscar + "%' order by cod_gastos desc";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
           
            
            while (rs.next()) {

                registros[0] = rs.getString("cod_gastos");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("valor");
                registros[3] = rs.getString("obs");

                totalRegistros = totalRegistros + 1;
               
                modelo.addRow(registros);
            }

            return modelo;
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
    
    
    
    
      public boolean insertar(Dgastos datos) {

        sSQL = "insert into gastos (descripcion,valor,obs) values (?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, datos.getDescripcion());
            pst.setLong(2, datos.getValor());
            pst.setString(3, datos.getObs());


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

    }/*CIERRE FUNCION INSERTAR*/
    
}
