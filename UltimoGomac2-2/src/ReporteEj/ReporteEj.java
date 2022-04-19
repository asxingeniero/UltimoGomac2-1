/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReporteEj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


// private conexion mysql = new conexion();
//    private Connection cn = mysql.conectar();
/**
 *
 * @author Ingeniero
 */
public class ReporteEj {
    public void ReporteEj1() throws JRException, SQLException{
        
        Connection a;        
        a = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=gomacv0rosa");
        JasperReport reporteEj=null;
        reporteEj=(JasperReport) JRLoader.loadObjectFromFile("/ReporteEj/report1.jasper");
        JasperPrint print=JasperFillManager.fillReport(reporteEj, null, a);
        JasperViewer ver=new JasperViewer(print);
        ver.setTitle("Ejempo");
        ver.setVisible(true);
        
        
    }
    
    
}
