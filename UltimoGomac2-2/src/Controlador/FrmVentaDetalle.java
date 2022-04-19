package Controlador;

import static Controlador.FRMPRINCIPAL.deskPricipal;
/*
import static Controlador.FrmVentaDetalle11.txtCantidadProducto;
import static Controlador.FrmVentaDetalle11.txtCod_cliente;
import static Controlador.FrmVentaDetalle11.txtCod_producto;
import static Controlador.FrmVentaDetalle11.txtCod_usuario;
import static Controlador.FrmVentaDetalle11.txtNombre_cliente;
import static Controlador.FrmVentaDetalle11.txtNombre_producto;
import static Controlador.FrmVentaDetalle11.txtPrecio_Prod_Mayor;
import static Controlador.FrmVentaDetalle11.txtPrecio_producto;
import static Controlador.FrmVentaDetalle11.txtStockDetalle;
import static Controlador.FrmVentaDetalle11.txtSubPrecioCompra;
*/
import static Controlador.FrmVistaProducto.comprobarProducto;
import static Controlador.FrmVistacliente.Comprueba;
import Datos.Ddetalle_venta;
import Datos.Dproducto;
import Datos.Dventa;
import Funciones.Fdetalle_venta;
import Funciones.Fproducto;

import Funciones.Fventa;
import Funciones.conexion;
//import ReporteEj.VistaBoletaEj;
import Reportes.VistaBoleta;
import java.awt.Component;
import java.net.InetAddress;

import java.sql.Connection;
import java.sql.Date;
import java.text.DecimalFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

// faltan importes
import javax.swing.JFrame;           // hacen falta en la otra clase
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public final class FrmVentaDetalle extends javax.swing.JInternalFrame {

    int foco;
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    public FrmVentaDetalle() {
        initComponents();
        //btnGuardar.setMnemonic(KeyEvent.VK_X);
        
        btnNuevo.setEnabled(false);
        btnCalcular.setEnabled(false);
        DetallesFormVenta(); // se inhabilitan los botones hasta que se inicie la venta
        lblModo.setLabelFor(cboModoIngreso);
        lblModo.setDisplayedMnemonic('y');
        txtDescuento.setFocusAccelerator('u');
        txtImporte.setFocusAccelerator('i');

        txtCantidadProducto.setFocusAccelerator('o');
        txtCod_producto.setFocusAccelerator('p');

        OcultaBotones();
        Calendar c2 = new GregorianCalendar();
        
        dcFecha_venta.setCalendar(c2);    // Erorr de libreria calendario por favor habilitar
        txtStockDetalle.setVisible(false);  // false
        txtCantidadProducto.setEditable(false);

        txtImporte.setEditable(true);
        txtDescuento.setEditable(true);

        btnClick.setVisible(false);

        txtNumFactura.setEditable(false);
        txtNumFactura.setText("0");
        btnBuscarCliente.requestFocus();

        txtSubPrecioCompra.setVisible(false);

        txtNombre_cliente.setText("Cliente General");
        txtCod_cliente.setText("2");
        
        
        /* Original
        btnNuevo.setEnabled(false);
        btnCalcular.setEnabled(false);
        DetallesFormVenta();
        lblModo.setLabelFor(cboModoIngreso);
        lblModo.setDisplayedMnemonic('y');
        txtDescuento.setFocusAccelerator('u');
        txtImporte.setFocusAccelerator('i');

        txtCantidadProducto.setFocusAccelerator('o');
        txtCod_producto.setFocusAccelerator('p');

        OcultaBotones();
        Calendar c2 = new GregorianCalendar();
        dcFecha_venta.setCalendar(c2);
        txtStockDetalle.setVisible(false);
        txtCantidadProducto.setEditable(false);

        txtImporte.setEditable(true);
        txtDescuento.setEditable(true);

        btnClick.setVisible(false);

        txtNumFactura.setEditable(false);
        txtNumFactura.setText("0");
        btnBuscarCliente.requestFocus();

        txtSubPrecioCompra.setVisible(false);

        txtNombre_cliente.setText("Cliente General");
        txtCod_cliente.setText("2");
        mostrar("0");
        */
        
        //    BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        //   bi.setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jTabla.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                //l.setBorder(new LineBorder(Color.black, 1));
                l.setBackground(new java.awt.Color(36, 33, 33));
                l.setForeground(new java.awt.Color(25, 118, 210));
                l.setFont(new java.awt.Font("Arial", 1, 12));
                return l;
            }
        });
    }

    public void ocultar_columnas() {

        jTabla.getColumnModel().getColumn(0).setMaxWidth(0);
        jTabla.getColumnModel().getColumn(0).setMinWidth(0);
        jTabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTabla.getColumnModel().getColumn(5).setMaxWidth(0);
        jTabla.getColumnModel().getColumn(5).setMinWidth(0);
        jTabla.getColumnModel().getColumn(5).setPreferredWidth(0);
        jTabla.getColumnModel().getColumn(6).setMaxWidth(0);
        jTabla.getColumnModel().getColumn(6).setMinWidth(0);
        jTabla.getColumnModel().getColumn(6).setPreferredWidth(0);
    }

    public void limpiarProductosDetalle() {
        txtCod_producto.setText("");
        txtNombre_producto.setText("");
        txtCantidadProducto.setText("");
        txtPrecio_producto.setText("");
        txtPrecio_Prod_Mayor.setText("");  // originalmente no tiene este campo
        
        txtDescuentoXP.setText("0");  // prueba
        /*
        txtCod_producto.setText("");
        txtNombre_producto.setText("");
        txtCantidadProducto.setText("");
        txtPrecio_producto.setText("");
        */
    }

    public void BuscarCodigoVenta() {

        Fventa funcion = new Fventa();
        int codigo = funcion.BuscarCodigoVenta();
        //ACA PODRIA PONER PARA EN UN LBL MOSTRAR EL NUMERO DE VENTA
        txtCod_venta.setText(String.valueOf(codigo));
        txtCod_ventaFK.setText(String.valueOf(codigo));
    }

    public void NfacturaAtxt() {
        DecimalFormat formateador = new DecimalFormat("000000");
        Fventa funcion = new Fventa();
        int Nfactura = funcion.BuscarNfacturas();

        Nfactura = Nfactura + 1;

        String format = formateador.format(Nfactura);

        txtNumFactura.setText(String.valueOf(format));

        
        
    }

    /**
     * ****BUSQUEDA SI EL CODIGO DEL PRODUCTO EXISTE**
     */
    public void seleccionProd() {
        
        // Venta con Scanner
    
        Fdetalle_venta funcion = new Fdetalle_venta();
        long cod_producto = funcion.selecProd();
        
         if (cod_producto > 0) {
            txtCod_producto.setText(String.valueOf(cod_producto));
            
            String nombre_producto = funcion.SelectNombre();
            txtNombre_producto.setText(String.valueOf(nombre_producto));

            int stock_producto = funcion.selecStock();  //  %%%%%%%%%%%%%%%%%%%%%
            txtStockDetalle.setText(String.valueOf(stock_producto));

            long precio_producto = funcion.selectPrecio();
            txtPrecio_producto.setText(String.valueOf(precio_producto));

            long precio_compra = funcion.selectPrecioCompra();
            txtSubPrecioCompra.setText(String.valueOf(precio_compra));
            
            
           long precio_v_mayor = funcion.selectPrecioVmayor();
            txtPrecio_Prod_Mayor.setText(String.valueOf(precio_v_mayor));
            
            if (cboModoIngreso.getSelectedItem() == "x Mayor") {
//                txtCantidadProducto.setEditable(true);
//                txtCantidadProducto.setText("");
//                txtCod_producto.setEditable(false);
//                foco = 1;
                
              // *//* TRAEMOS LA VENTA DEL BOTON (+)             
              insertarDetalleMayor();
             /*
                ** SE FINALIZA LA VENTA CON SCANNER AL POR MAYOR
                **
              */
            } else if (cboModoIngreso.getSelectedItem() == "x Detal") {

                foco = 0;
                // Venta con Scanner
                insertarDetalle();
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "No existe el codigo en el sistema");
            txtCod_producto.requestFocus();
            txtCod_producto.setText("");
        }
        
        /* Original
        if (cod_producto > 0) {
            txtCod_producto.setText(String.valueOf(cod_producto));

            String nombre_producto = funcion.SelectNombre();
            txtNombre_producto.setText(String.valueOf(nombre_producto));

            int stock_producto = funcion.selecStock();
            txtStockDetalle.setText(String.valueOf(stock_producto));

            Double precio_producto = funcion.selectPrecio();
            txtPrecio_producto.setText(String.valueOf(precio_producto));

            Double precio_compra = funcion.selectPrecioCompra();
            txtSubPrecioCompra.setText(String.valueOf(precio_compra));

            if (cboModoIngreso.getSelectedItem() == "x Mayor") {
                txtCantidadProducto.setEditable(true);
                txtCantidadProducto.setText("");
                txtCod_producto.setEditable(false);
                foco = 1;
            } else if (cboModoIngreso.getSelectedItem() == "x Unidad") {

                foco = 0;
                insertarDetalle();
            }

        } else {
            JOptionPane.showMessageDialog(null, "No existe el codigo en el sistema");
            txtCod_producto.requestFocus();
            txtCod_producto.setText("");
        }
      */
    }

    public void insertarDetalle() {

        // Este metodo lo utiliza cuando se pasa con scanner
        // En este metodo se realizan las sumas de las cosas
        int cantidad = 1;
        long Stock = Long.valueOf(txtStockDetalle.getText());
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad");
            return;
        }
        if (cantidad > Stock) {
            JOptionPane.showMessageDialog(null, "La cantidad a vender supera el Stock del producto.");
            txtCod_producto.setText("");
            txtNombre_producto.setText("");
            txtPrecio_producto.setText("");
            txtStockDetalle.setText("");
            txtCod_producto.requestFocus();

            return;
        }
        // Precio de venta del producto
        long PrecioPori = Long.parseLong(txtPrecio_producto.getText());
        // hace falta  campo precio por mayor se pasara en 0 ?
//        long precio_v_mayor = 0;
        // Cantidad
        long CantProd = Long.parseLong(txtCantidadProducto.getText());
        // precio de costo
        long subPreC = Long.parseLong(txtSubPrecioCompra.getText());
        // campo Descuento venta por unidad ?
        long descPProd = Long.parseLong(txtDescuentoXP.getText());
        // valor del campo descuento convertido a tipo double
        double descPProd2 = (double) descPProd;
        // Alistamos los insumos
        // 1) cien = 100
        double ciien = 100;
        // operacion 1 de 4 - campo descuento dividido en 100 
        double op1 = descPProd2/ciien;
        // operacion 2 de 4 precio del producto * resultado de op1
        double op2 = PrecioPori  * op1;
         // operacion 3 de 4 precio del producto - op2
        double op4 = PrecioPori - op2;
        // operacion 4 de 4; precio del producto redondeado
        long PrecioP = Math.round(op4);
//        JOptionPane.showMessageDialog(null, "Resultado del precio con descuento " + PrecioP);
        // Ahora el precio de venta tiene incluido el descuento por producto
        // Precio venta detal * cantidad
        long resultadoDetalle = PrecioP * CantProd;
        // cantidad * precio de costo
        long resultadoDetalle2 = CantProd * subPreC;

        Ddetalle_venta datos = new Ddetalle_venta();
        Fdetalle_venta funcion = new Fdetalle_venta();

        datos.setCantidad_detalle(Integer.parseInt(txtCantidadProducto.getText()));
        datos.setCod_productoFK(Long.valueOf(txtCod_producto.getText()));
        datos.setCod_ventaFK(Integer.parseInt(txtCod_ventaFK.getText()));
        datos.setPrecio_producto(PrecioP);
         // Precio venta detal * cantidad                
        datos.setSubtotal(resultadoDetalle);
        // cantidad * precio de costo
        datos.setSubPrecioCompra(resultadoDetalle2);
        datos.setPrecio_compra(subPreC);
        
//        datos.setPrecio_xm(precio_v_mayor);
        datos.setDescuento_xp(descPProd);
        
        
        if (funcion.insertarDetalle(datos)) {
            // Precio del producto
            long precio_producto_sin_desc = Long.parseLong(txtPrecio_producto.getText());
            // Suma de subtotales del producto
            long sub_total = Long.parseLong(txtSubTotal.getText());
            // Descuento aplicado a la venta total
            long descuento = Long.parseLong(txtDescuento.getText());
            // desc x unidad
           long desc_por_prod =  Long.parseLong(txtDescuentoXP.getText());
           
           //Se realizan las operaciones de descuento aplicado por producto
           //alistamiento de insumos
           // 100
           double ciien2 = 100;
           // convertimos el descuento por producto a double
           double desc_por_prod2 = (double) desc_por_prod;
           // convertimos a double el precio del producto
           double precio_producto_sin_desc2 = (double) precio_producto_sin_desc;
           
           // Se realizan las operaciones de aplicar el descuento al precio
           double opera1 = desc_por_prod2 / ciien2;
           double opera2 = precio_producto_sin_desc2 * opera1;
           double opera3 = precio_producto_sin_desc2 - opera2;
           
           // convertimos a long el resultado del producto con el descuento aplicado
           long precio_producto = Math.round(opera3);
                                  
            long total = precio_producto * cantidad;
            long resultado = total + sub_total;
            long descuento2 = Math.round(resultado * (descuento * 0.01));
            long resultadoDescuento = resultado - descuento2;

            txtSubTotal.setText(String.valueOf(resultado));

            txtTotal_venta.setText(String.valueOf(resultadoDescuento));
            // txtTotal_venta.setText(String.valueOf(resultadoDescuento));

            Dventa datos1 = new Dventa();
            Fventa funcion1 = new Fventa();
            datos1.setCod_venta(Integer.parseInt(txtCod_ventaFK.getText()));
            datos1.setTotal_venta(Long.valueOf(txtTotal_venta.getText()));
            funcion1.Total(datos1);

            Dproducto datos2 = new Dproducto();
            Fproducto funcion2 = new Fproducto();
            int stock = 0;
            datos2.setCod_producto(Long.valueOf(txtCod_producto.getText()));
            stock = Integer.parseInt(txtStockDetalle.getText());
            stock = stock - cantidad;
            datos2.setStock_producto(stock);
            funcion2.ModificarStockProductos(datos2);
        } else {
            JOptionPane.showMessageDialog(null, "El codigo ingresado no esta en el sistema");
        }
        txtCod_producto.setText("");
        txtPrecio_producto.setText("");
        txtCantidadProducto.setText("1");
        txtNombre_producto.setText("");
        txtCantidadProducto.setEditable(false);
        txtSubPrecioCompra.setText("");

    } // FINAL DE LA VENTA CON SCANNER AL DETAL
    
    /*
      **
      ** Inicio de la venta  con scanner al por mayor
      **
    */
    
    private void insertarDetalleMayor() {
        // Este metodo se utiliza en la venta con scanner
        
        int cantidad = 1;
        long Stock = Long.valueOf(txtStockDetalle.getText());
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad");
            return;
        }
        if (cantidad > Stock) {
            JOptionPane.showMessageDialog(null, "La cantidad a vender supera el Stock del producto.");
            txtCod_producto.setText("");
            txtNombre_producto.setText("");
            txtPrecio_producto.setText("");
            txtStockDetalle.setText("");
            txtCod_producto.requestFocus();

            return;
        }
        // Precio de venta del producto
//        long PrecioPori = Long.parseLong(txtPrecio_producto.getText());
         long PrecioPori = 0;
        // hace falta  campo precio por mayor se pasara en 0 ?
         long precio_v_mayor = Long.parseLong(txtPrecio_Prod_Mayor.getText());
//        long precio_v_mayor = 0;
        // Cantidad a llevar por el cliente
        long CantProd = Long.parseLong(txtCantidadProducto.getText());
        // precio de costo
        long subPreC = Long.parseLong(txtSubPrecioCompra.getText());
        // campo Descuento venta por unidad ?
//        long descPProd = Long.parseLong(txtDescuentoXP.getText());
         long descPProd = 0;
        // valor del campo descuento convertido a tipo double
//        double descPProd2 = (double) descPProd;
        // Alistamos los insumos
        // 1) cien = 100
//        double ciien = 100;
        // operacion 1 de 4 - campo descuento dividido en 100 
//        double op1 = descPProd2/ciien;
        // operacion 2 de 4 precio del producto * resultado de op1
//        double op2 = PrecioPori  * op1;
         // operacion 3 de 4 precio del producto - op2
//        double op4 = PrecioPori - op2;
        // operacion 4 de 4; precio del producto redondeado
        long PrecioP = Math.round(precio_v_mayor);
//        JOptionPane.showMessageDialog(null, "Resultado del precio con descuento " + PrecioP);
        // Ahora el precio de venta tiene incluido el descuento por producto
        // Precio venta detal * cantidad
        long resultadoDetalle = PrecioP * CantProd;
        // cantidad * precio de costo
        long resultadoDetalle2 = CantProd * subPreC;

        Ddetalle_venta datos = new Ddetalle_venta();
        Fdetalle_venta funcion = new Fdetalle_venta();

        datos.setCantidad_detalle(Integer.parseInt(txtCantidadProducto.getText()));
        datos.setCod_productoFK(Long.valueOf(txtCod_producto.getText()));
        datos.setCod_ventaFK(Integer.parseInt(txtCod_ventaFK.getText()));
        datos.setPrecio_producto(PrecioP);
         // Precio venta detal * cantidad                
        datos.setSubtotal(resultadoDetalle);
        // cantidad * precio de costo
        datos.setSubPrecioCompra(resultadoDetalle2);
        datos.setPrecio_compra(subPreC);
        
        datos.setPrecio_V_mayor(precio_v_mayor);
        datos.setDescuento_xp(descPProd);
        
        
        if (funcion.insertarDetalle(datos)) {
            // Precio del producto
//            long precio_producto_sin_desc = Long.parseLong(txtPrecio_producto.getText());
            long precio_producto_sin_desc = Long.parseLong(txtPrecio_Prod_Mayor.getText());
            // Suma de subtotales del producto
            long sub_total = Long.parseLong(txtSubTotal.getText());
            // Descuento aplicado a la venta total
            long descuento = Long.parseLong(txtDescuento.getText());
            // desc x unidad
           long desc_por_prod =  Long.parseLong(txtDescuentoXP.getText());
           
           //Se realizan las operaciones de descuento aplicado por producto
           //alistamiento de insumos
           // 100
           double ciien2 = 100;
           // convertimos el descuento por producto a double
           double desc_por_prod2 = (double) desc_por_prod;
           // convertimos a double el precio del producto
           double precio_producto_sin_desc2 = (double) precio_producto_sin_desc;
           
           // Se realizan las operaciones de aplicar el descuento al precio
           double opera1 = desc_por_prod2 / ciien2;
           double opera2 = precio_producto_sin_desc2 * opera1;
//           double opera3 = precio_producto_sin_desc2 - opera2;
           double opera3 = precio_producto_sin_desc2;
           
           // convertimos a long el resultado del producto con el descuento aplicado
           long precio_producto = Math.round(opera3);
                                  
            long total = precio_producto * cantidad;
            long resultado = total + sub_total;
            long descuento2 = Math.round(resultado * (descuento * 0.01));
            long resultadoDescuento = resultado - descuento2;

            txtSubTotal.setText(String.valueOf(resultado));

            txtTotal_venta.setText(String.valueOf(resultadoDescuento));
            // txtTotal_venta.setText(String.valueOf(resultadoDescuento));

            Dventa datos1 = new Dventa();
            Fventa funcion1 = new Fventa();
            datos1.setCod_venta(Integer.parseInt(txtCod_ventaFK.getText()));
            datos1.setTotal_venta(Long.valueOf(txtTotal_venta.getText()));
            funcion1.Total(datos1);

            Dproducto datos2 = new Dproducto();
            Fproducto funcion2 = new Fproducto();
            int stock = 0;
            datos2.setCod_producto(Long.valueOf(txtCod_producto.getText()));
            stock = Integer.parseInt(txtStockDetalle.getText());
            stock = stock - cantidad;
            datos2.setStock_producto(stock);
            funcion2.ModificarStockProductos(datos2);
        } else {
            JOptionPane.showMessageDialog(null, "El codigo ingresado no esta en el sistema");
        }
        txtCod_producto.setText("");
        txtPrecio_producto.setText("");
        txtCantidadProducto.setText("1");
        txtNombre_producto.setText("");
        txtCantidadProducto.setEditable(false);
        txtSubPrecioCompra.setText("");
    }
    /*
     **
     ** FIN DE LA VENTA CON SCANNER AL POR MAYOR
     **
    */


    public void mostrar(String cod_venta) {
        try {
            DefaultTableModel modelo;
            Fdetalle_venta func = new Fdetalle_venta();
            modelo = func.mostrar(cod_venta);
            jTabla.setModel(modelo);
            ocultar_columnas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void DetallesFormVenta() {
        txtCod_usuario.setVisible(false);
        txtCod_cliente.setVisible(false);
        txtCod_venta.setVisible(false);
        txtNombre_cliente.setEditable(false);
        txtCod_detalle.setVisible(false);
        txtCod_ventaFK.setVisible(false);
        txtNombre_producto.setEditable(false);
        txtCantidadProducto.setEditable(false);
        txtPrecio_producto.setEditable(false);
        txtCod_producto.setEditable(false);
        txtTotal_venta.setEditable(false);
        txtDescuentoXP.setEditable(false);
        txtPrecio_Prod_Mayor.setEditable(false);
    }

    public void DetallesFormVentaProd() {
        txtCantidadProducto.setEditable(true);
        txtPrecio_producto.setEditable(false);
        
        txtDescuentoXP.setEditable(true);  // ensayo 0305
    }

    public void OcultaBotones() {
        btnbuscarProducto.setEnabled(false);
        btnAgregarProducto.setEnabled(false);
        btnQuitarProducto.setEnabled(false);
    }

    public void activaBotones() {
        btnbuscarProducto.setEnabled(true);
        btnAgregarProducto.setEnabled(true);
        btnQuitarProducto.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboComprobante = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre_cliente = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNumFactura = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();
        dcFecha_venta = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTabla = jTabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtTotal_venta = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnCalcular = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCod_producto = new javax.swing.JTextField();
        txtNombre_producto = new javax.swing.JTextField();
        txtPrecio_producto = new javax.swing.JTextField();
        txtCantidadProducto = new javax.swing.JTextField();
        btnAgregarProducto = new javax.swing.JButton();
        btnbuscarProducto = new javax.swing.JButton();
        btnQuitarProducto = new javax.swing.JButton();
        cboModoIngreso = new javax.swing.JComboBox<>();
        lblModo = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtDescuentoXP = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtPrecio_Prod_Mayor = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        cboMetodoPago = new javax.swing.JComboBox<>();
        txtCod_cliente = new javax.swing.JTextField();
        txtCod_usuario = new javax.swing.JTextField();
        txtCod_ventaFK = new javax.swing.JTextField();
        txtCod_detalle = new javax.swing.JTextField();
        txtCod_venta = new javax.swing.JTextField();
        txtStockDetalle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombre_usuario = new javax.swing.JLabel();
        btnClick = new javax.swing.JButton();
        txtSubPrecioCompra = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel2.setBackground(new java.awt.Color(202, 166, 219));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel3.setBackground(new java.awt.Color(202, 166, 219));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("  Fecha :");

        cboComprobante.setBackground(new java.awt.Color(36, 33, 33));
        cboComprobante.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boleta" }));
        cboComprobante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboComprobanteItemStateChanged(evt);
            }
        });
        cboComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboComprobanteActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("N° :");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText(" Cliente :");

        txtNombre_cliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNombre_cliente.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtNombre_cliente.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNombre_cliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        btnGuardar.setBackground(new java.awt.Color(36, 33, 33));
        btnGuardar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(207, 207, 207));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/guardar.png"))); // NOI18N
        btnGuardar.setMnemonic('x');
        btnGuardar.setText("Iniciar ");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("    Tipo :");

        txtNumFactura.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNumFactura.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtNumFactura.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNumFactura.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNumFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumFacturaKeyTyped(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(36, 33, 33));
        btnNuevo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(207, 207, 207));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/nuevo.png"))); // NOI18N
        btnNuevo.setMnemonic('n');
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnBuscarCliente.setBackground(new java.awt.Color(36, 33, 33));
        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/buscar.png"))); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboComprobante, 0, 189, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(dcFecha_venta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtNombre_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNumFactura))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnBuscarCliente))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcFecha_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtNombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(5, 5, 5))))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(cboComprobante))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabla.setBackground(new java.awt.Color(204, 204, 204));
        jTabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cod producto", "Nombre", "Precio", "Cantidad"
            }
        ));
        jTabla.setRowHeight(20);
        jTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTabla);

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jPanel6.setBackground(new java.awt.Color(238, 238, 238));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("IMPORTE");

        txtImporte.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtImporte.setText("0");
        txtImporte.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("CAMBIO");

        txtCambio.setEditable(false);
        txtCambio.setBackground(new java.awt.Color(255, 255, 255));
        txtCambio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtCambio.setText("0");
        txtCambio.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtCambio.setSelectionColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(86, 86, 86))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(txtImporte, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(txtCambio)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(4, 4, 4)
                .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jPanel7.setBackground(new java.awt.Color(238, 238, 238));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("SUB TOTAL");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("TOTAL VENTA");

        txtSubTotal.setEditable(false);
        txtSubTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtSubTotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtSubTotal.setText("0");
        txtSubTotal.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSubTotal.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSubTotal.setVerifyInputWhenFocusTarget(false);

        txtTotal_venta.setEditable(false);
        txtTotal_venta.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal_venta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTotal_venta.setText("0");
        txtTotal_venta.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTotal_venta.setSelectedTextColor(new java.awt.Color(0, 0, 0));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/menu/coins17.png"))); // NOI18N

        txtDescuento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtDescuento.setText("0");
        txtDescuento.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtDescuento.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtDescuento.setVerifyInputWhenFocusTarget(false);
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("%");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("DESCUENTO");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel20)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addComponent(txtTotal_venta)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(txtTotal_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCalcular.setBackground(new java.awt.Color(36, 33, 33));
        btnCalcular.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCalcular.setMnemonic('c');
        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCalcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(202, 166, 219));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Cantidad :");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Precio :");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Producto :");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Codigo :");

        txtCod_producto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCod_producto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtCod_producto.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCod_producto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCod_producto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCod_productoCaretUpdate(evt);
            }
        });
        txtCod_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCod_productoActionPerformed(evt);
            }
        });
        txtCod_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCod_productoKeyTyped(evt);
            }
        });

        txtNombre_producto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNombre_producto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtNombre_producto.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNombre_producto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNombre_producto.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                txtNombre_productoMouseWheelMoved(evt);
            }
        });
        txtNombre_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre_productoActionPerformed(evt);
            }
        });

        txtPrecio_producto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtPrecio_producto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtPrecio_producto.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPrecio_producto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPrecio_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecio_productoActionPerformed(evt);
            }
        });

        txtCantidadProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCantidadProducto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(207, 207, 207)));
        txtCantidadProducto.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCantidadProducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCantidadProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadProductoActionPerformed(evt);
            }
        });
        txtCantidadProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyTyped(evt);
            }
        });

        btnAgregarProducto.setBackground(new java.awt.Color(36, 33, 33));
        btnAgregarProducto.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnAgregarProducto.setForeground(new java.awt.Color(207, 207, 207));
        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/Agregarr.png"))); // NOI18N
        btnAgregarProducto.setMnemonic('a');
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnbuscarProducto.setBackground(new java.awt.Color(36, 33, 33));
        btnbuscarProducto.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnbuscarProducto.setForeground(new java.awt.Color(207, 207, 207));
        btnbuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/buscar.png"))); // NOI18N
        btnbuscarProducto.setText(" ");
        btnbuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarProductoActionPerformed(evt);
            }
        });

        btnQuitarProducto.setBackground(new java.awt.Color(36, 33, 33));
        btnQuitarProducto.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnQuitarProducto.setForeground(new java.awt.Color(207, 207, 207));
        btnQuitarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesForm/Quitar.png"))); // NOI18N
        btnQuitarProducto.setMnemonic('s');
        btnQuitarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProductoActionPerformed(evt);
            }
        });

        cboModoIngreso.setBackground(new java.awt.Color(36, 33, 33));
        cboModoIngreso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboModoIngreso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "x Detal", "x Mayor" }));
        cboModoIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboModoIngresoActionPerformed(evt);
            }
        });

        lblModo.setBackground(new java.awt.Color(255, 255, 255));
        lblModo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblModo.setText("Modo :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblModo, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cboModoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtCod_producto)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCantidadProducto)
                                        .addGap(3, 3, 3))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(191, 191, 191)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNombre_producto)))
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(277, 277, 277)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbuscarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(btnQuitarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnbuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboModoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCod_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrecio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(btnQuitarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(202, 166, 219));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descuento X Producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        txtDescuentoXP.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtDescuentoXP.setText("0");
        txtDescuentoXP.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("%");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(txtDescuentoXP, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescuentoXP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(202, 166, 219));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Precio X Mayor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        txtPrecio_Prod_Mayor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(txtPrecio_Prod_Mayor, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(txtPrecio_Prod_Mayor)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(202, 166, 219));

        cboMetodoPago.setBackground(new java.awt.Color(36, 33, 33));
        cboMetodoPago.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Credito", "Tarjeta" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addContainerGap())
        );

        txtCod_ventaFK.setText(" ");

        txtCod_detalle.setText(" ");

        txtCod_venta.setText(" ");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("USUARIO :");

        txtNombre_usuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNombre_usuario.setText("Vendedor");

        btnClick.setText("prod");
        btnClick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClickActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("FORMULARIO DE VENTAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClick)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStockDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCod_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCod_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCod_ventaFK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCod_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCod_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 72, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCod_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCod_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCod_ventaFK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCod_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCod_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStockDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre_usuario)
                    .addComponent(jLabel5)
                    .addComponent(btnClick)
                    .addComponent(txtSubPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

         // con este boton se inicia el proces de venta de los productos
        /*PARTE VALIDACION DE CAMPOS*/
        if (txtCod_cliente.getText().length() == 0 || txtNombre_cliente.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un Cliente");
            btnBuscarCliente.requestFocus();
            return;
        }
        if (txtDescuento.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Este campo debe llevar un valor");
            txtDescuento.requestFocus();
            return;
        }
        if (txtDescuentoXP.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Este campo debe llevar un valor");
            txtDescuentoXP.requestFocus();
            return;
        }
        if (txtImporte.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Este campo debe llevar un valor");
            txtImporte.requestFocus();
            return;
        }
    
        this.setClosable(false);
        btnNuevo.setEnabled(true);
        txtImporte.setEditable(true);
        txtDescuento.setEditable(false); 
        
        // llama las clases de 
        Dventa datos = new Dventa();
        Fventa funcion = new Fventa();
        
        Calendar cal;
        int d, m, a;
        cal = dcFecha_venta.getCalendar();  /// error de libreria de fechas
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        datos.setFecha_venta(new Date(a, m, d));

        datos.setTotal_venta(Long.valueOf(txtTotal_venta.getText())); // esta en cero

        datos.setCod_usuarioFK(Integer.parseInt(txtCod_usuario.getText()));
        datos.setCod_clienteFK(Integer.parseInt(txtCod_cliente.getText()));

        int comprobante = cboComprobante.getSelectedIndex();
        datos.setTipo_comprobante((String) cboComprobante.getItemAt(comprobante));
        datos.setNum_factura(Integer.parseInt(txtNumFactura.getText()));
        datos.setDescuento(Long.parseLong(txtDescuento.getText()));
        
        
    // Falta realizar la insercion del campo de descuento por producto    
        
        datos.setPago(Long.parseLong(txtImporte.getText()));

        if (funcion.insertar(datos)) {  // se crea en la tabla venta
            DetallesFormVentaProd();
            BuscarCodigoVenta();
            activaBotones();
            btnGuardar.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se Ingresaron Datos");
        }

        txtCod_producto.setEditable(true);
        txtCod_producto.requestFocus();
        txtCantidadProducto.setEditable(false);
        txtCantidadProducto.setText("1");
        btnCalcular.setEnabled(true);
        
        /*PARTE VALIDACION DE CAMPOS*/
        /*
        if (txtCod_cliente.getText().length() == 0 || txtNombre_cliente.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un Cliente");
            btnBuscarCliente.requestFocus();
            return;
        }
        if (txtDescuento.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Este campo debe llevar un valor");
            txtDescuento.requestFocus();
            return;
        }
        if (txtImporte.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Este campo debe llevar un valor");
            txtImporte.requestFocus();
            return;
        }

        this.setClosable(false);
        btnNuevo.setEnabled(true);
        txtImporte.setEditable(true);
        txtDescuento.setEditable(false);
        Dventa datos = new Dventa();
        Fventa funcion = new Fventa();
        Calendar cal;
        int d, m, a;
        cal = dcFecha_venta.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        datos.setFecha_venta(new Date(a, m, d));

        datos.setTotal_venta(Double.valueOf(txtTotal_venta.getText()));

        datos.setCod_usuarioFK(Integer.parseInt(txtCod_usuario.getText()));
        datos.setCod_clienteFK(Integer.parseInt(txtCod_cliente.getText()));

        int comprobante = cboComprobante.getSelectedIndex();
        datos.setTipo_comprobante((String) cboComprobante.getItemAt(comprobante));
        datos.setNum_factura(Integer.parseInt(txtNumFactura.getText()));
        datos.setDescuento(Double.parseDouble(txtDescuento.getText()));
        datos.setPago(Double.parseDouble(txtImporte.getText()));

        if (funcion.insertar(datos)) {
            DetallesFormVentaProd();
            BuscarCodigoVenta();
            activaBotones();
            btnGuardar.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se Ingresaron Datos");
        }

        txtCod_producto.setEditable(true);
        txtCod_producto.requestFocus();
        txtCantidadProducto.setEditable(false);
        txtCantidadProducto.setText("1");
        btnCalcular.setEnabled(true);
      */
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnbuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarProductoActionPerformed
        comprobarProducto = 2;
        FrmVistaProducto form = new FrmVistaProducto();
        deskPricipal.add(form);
        //   form.setIconifiable(true);
        // form.setMaximizable(false);
        form.toFront();
        form.setVisible(true);

    }//GEN-LAST:event_btnbuscarProductoActionPerformed

    private void jTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaMouseClicked

        txtCod_producto.setEditable(false);
        txtNombre_producto.setEditable(false);
        txtCantidadProducto.setEditable(false);
        txtPrecio_producto.setEditable(false);
        btnQuitarProducto.setEnabled(true);
        
        
        
        int fila = jTabla.rowAtPoint(evt.getPoint());
        txtCod_detalle.setText(jTabla.getValueAt(fila, 0).toString());
        txtCod_producto.setText(jTabla.getValueAt(fila, 1).toString());
        txtNombre_producto.setText(jTabla.getValueAt(fila, 2).toString());
        txtPrecio_producto.setText(jTabla.getValueAt(fila, 3).toString());
        txtCantidadProducto.setText(jTabla.getValueAt(fila, 4).toString());
        txtCod_ventaFK.setText(jTabla.getValueAt(fila, 5).toString());
        txtStockDetalle.setText(jTabla.getValueAt(fila, 6).toString());
    }//GEN-LAST:event_jTablaMouseClicked

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
                          
                   // boton (+)
        if (txtCod_producto.getText().length() == 0 || txtNombre_producto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un Producto.");
            btnbuscarProducto.requestFocus();
            return;
        }
        if (txtCantidadProducto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese una Cantidad.");
            txtCantidadProducto.requestFocus();
            return;
        }
        if (txtPrecio_producto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un precio.");
            txtPrecio_producto.requestFocus();
            return;
        }
        
        if (txtNombre_producto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre.");
            txtNombre_producto.requestFocus();
            return;
        }
        
        if (txtSubPrecioCompra.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un  sub precio.");
            txtSubPrecioCompra.requestFocus();
            return;
        }
        
          if (txtDescuentoXP.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "El valor debe estar en 0");
            txtDescuentoXP.requestFocus();
            return;
        }
        
         // Fin de validacion de campos vacios
        
       
         // Modo venta al detal   
       if (cboModoIngreso.getSelectedItem() == "x Detal") {
           
           
        int Cantidad = Integer.parseInt(txtCantidadProducto.getText());
        long Stock = Long.valueOf(txtStockDetalle.getText()); // Stock actual
        if (Cantidad > Stock) {
            JOptionPane.showMessageDialog(null, "La cantidad a vender supera el Stock del producto.");
            txtCantidadProducto.setText("");
            txtCantidadProducto.requestFocus();
            return;
        }
        //;
          // Alistamos la venta x Detal
        // Precio de venta sin descuento  
        long precio_prod_sin_desc = Long.parseLong(txtPrecio_producto.getText()); 
        // cantidad del producto
//        int cantidad_prod = Integer.parseInt(txtCantidadProducto.getText());
         int cantidad_prod = Integer.parseInt(txtCantidadProducto.getText());
        // precio de compra del producto
        long sub_precioC = Long.parseLong(txtSubPrecioCompra.getText());
        // descuento por producto
        long campo_desc_v_detal = Long.parseLong(txtDescuentoXP.getText());
         // el precio por mayor lo enviamos a 0
        long precio_v_mayor = 0; 
        
        // Se realizan las operaciones de venta con descuento por producto
        // 1. insumos
        // 100
        double ciien3 = 100;
        // convertir el descuento a double
        double campo_desc_v_detal2 = (double)campo_desc_v_detal;
        // convierte el precio al double
        double precio_prod_sin_desc2 = (double) precio_prod_sin_desc;
        // se convierte la cantidad del producto a double
//        double cantidad_p2 = (double)cantidad_p;
//        JOptionPane.showMessageDialog(null, "Precio del producto " + precio_prod_sin_desc2);
        // Se realizan las operaciones 
        // opera1 se divide el campo descuento / 100
        double opera1 = campo_desc_v_detal2 / ciien3;
//           JOptionPane.showMessageDialog(null, "Operacion 1 precio sin descuento dividido cien " + opera1 );
        
        // se multiplica el precio por la opera1
        double opera2 = precio_prod_sin_desc2 * opera1;
//        JOptionPane.showMessageDialog(null, "Operacion 2 precio *  ope 1 " + opera2 );
        // se resta el precio sin descuento menos opera2
        double opera3 = precio_prod_sin_desc2 - opera2;
        
        long precio_prod = Math.round(opera3);
        
        
        
        
            // total precio de venta * cantidad
        long resultado1 = precio_prod * cantidad_prod; 
            // cantidad * precio de costo
        long resultado2 = cantidad_prod * sub_precioC;

        Ddetalle_venta datos = new Ddetalle_venta();
        Fdetalle_venta funcion = new Fdetalle_venta();

        datos.setCantidad_detalle(Integer.parseInt(txtCantidadProducto.getText()));
        datos.setCod_productoFK(Long.valueOf(txtCod_producto.getText()));
        // precio venta unidad 
        datos.setPrecio_producto(precio_prod); 
        // El precio por mayor lo pasamos a 0
        datos.setPrecio_V_mayor(precio_v_mayor);
        
        datos.setCod_ventaFK(Integer.parseInt(txtCod_ventaFK.getText()));
        // total precio de venta * cantidad
        datos.setSubtotal(resultado1);
         // cantidad * precio de costo
        datos.setSubPrecioCompra(resultado2);
        // 
        datos.setPrecio_compra(Long.parseLong(txtSubPrecioCompra.getText()));
        
//        datos.setPrecio_V_mayor(Long.parseLong(txtPrecio_Prod_Mayor.getText()));
//        
//        JOptionPane.showMessageDialog(null, );
                
        
        datos.setDescuento_xp(campo_desc_v_detal);
        
        // el campo seleccionado es el de precio de venta para realizar esta venta 
        // por unidad

        if (funcion.insertar(datos)) {

            long valorProdP = Long.parseLong(txtPrecio_producto.getText());
            long valorSub = Long.parseLong(txtSubTotal.getText());

            long descuento = Long.parseLong(txtDescuento.getText());
            
            long descuento3 = Long.parseLong(txtDescuentoXP.getText());
            
            
            // descuento x producto
            long porcentaje = ((valorProdP * descuento3)/100)* cantidad_prod;
                                  
            long descuento4 = Math.round(valorProdP * cantidad_prod) - porcentaje;                      
             // pasar el descuento a el campo de texto del total
            
             
            long total = descuento4;
            long resultado = total + valorSub;
             
           /*  original  30 - 04 - 2021  
            long total = valorProdP * Cantidad;
            long resultado = total + valorSub;
           */ 
            

            long descuento2 = Math.round(resultado * (descuento * 0.01));

            long resultadoDescuento = resultado - descuento2;

            txtSubTotal.setText(String.valueOf(resultado));
            //total de la venta 
            txtTotal_venta.setText(String.valueOf(resultadoDescuento));

            Dventa datos1 = new Dventa();
            Fventa funcion1 = new Fventa();
            datos1.setCod_venta(Integer.parseInt(txtCod_ventaFK.getText()));
            datos1.setTotal_venta(Long.valueOf(txtTotal_venta.getText()));
            funcion1.Total(datos1);
        
            /**
             * ****Quitar Stock*+++++++++
             */
            
            
            Dproducto datos2 = new Dproducto();
            Fproducto funcion2 = new Fproducto();
            int stock = 0;
            int cantidad = 0;
            datos2.setCod_producto(Long.valueOf(txtCod_producto.getText()));
            stock = Integer.parseInt(txtStockDetalle.getText());
            cantidad = Integer.parseInt(txtCantidadProducto.getText());
            stock = stock - cantidad;
            datos2.setStock_producto(stock);
            funcion2.ModificarStockProductos(datos2);
        } else {
            JOptionPane.showMessageDialog(null, "Error Ingreso Producto Form");
        }
        mostrar(txtCod_ventaFK.getText());
        limpiarProductosDetalle();
        txtPrecio_producto.setEditable(false);
        txtCantidadProducto.setText("");    // originalmente ay un 1
        cboModoIngreso.setSelectedIndex(0);
        txtCod_producto.setEditable(true);
        txtCod_producto.requestFocus();
        txtCantidadProducto.setEditable(false);
        txtPrecio_Prod_Mayor.setText("");
        txtPrecio_Prod_Mayor.setEditable(false);  
        
        txtDescuentoXP.setEditable(false); 
        
     // Modo venta por mayor ####################          
        } else if(cboModoIngreso.getSelectedItem() == "x Mayor")  {

        int Cantidad = Integer.parseInt(txtCantidadProducto.getText());
        long Stock = Long.valueOf(txtStockDetalle.getText());
        if (Cantidad > Stock) {
            JOptionPane.showMessageDialog(null, "La cantidad a vender supera el Stock del producto.");
            txtCantidadProducto.setText("");
            txtCantidadProducto.requestFocus();
            return;
        }
        ;
        
        // Precio del producto al detal
//        long precio_prod = Long.parseLong(txtPrecio_producto.getText());

        // Precio del producto al por mayor
        long precio_prod_mayor = Long.parseLong(txtPrecio_Prod_Mayor.getText());
        // Cantidad del producto
        int cantidad_prod = Integer.parseInt(txtCantidadProducto.getText());
        // Precio de compra del producto
        long sub_precioC = Long.parseLong(txtSubPrecioCompra.getText());

          // JOptionPane.showMessageDialog(null, precio_prod);
        
 //        Operación 1 utilizando el precio del producto al detal * cantidad 
//        long resultado1 = precio_prod * cantidad_prod;
      
    //    Operacion 2 utilizando el precio del producto con el valor al por mayor
        long resultado1 = precio_prod_mayor * cantidad_prod;
   
        long resultado2 = cantidad_prod * sub_precioC;

        Ddetalle_venta datos = new Ddetalle_venta();
        Fdetalle_venta funcion = new Fdetalle_venta();

        datos.setCantidad_detalle(Integer.parseInt(txtCantidadProducto.getText()));
        datos.setCod_productoFK(Long.valueOf(txtCod_producto.getText()));
        
        // prueba campo precio al por mayor 03052021 6: am
       // datos.setPrecio_producto(precio_prod);
//       datos.setPrecio_producto(precio_prod);
       
//      insertamos en la bd el precio del producto con el valor al por mayor
        datos.setPrecio_producto(precio_prod_mayor);
        
        datos.setCod_ventaFK(Integer.parseInt(txtCod_ventaFK.getText()));
        datos.setSubtotal(resultado1);
        datos.setSubPrecioCompra(resultado2);
        datos.setPrecio_compra(Long.parseLong(txtSubPrecioCompra.getText()));
        
        datos.setPrecio_V_mayor(precio_prod_mayor);

        if (funcion.insertar(datos)) {
            
        //  valor del producto al detal   
        //    long valorProdP = Long.parseLong(txtPrecio_producto.getText());
        
        // valor del producto al por mayor
           long valorProdP_mayor = Long.parseLong(txtPrecio_Prod_Mayor.getText());
          // subtotal
            long valorSub = Long.parseLong(txtSubTotal.getText());
            // campo del descuento en venta total
            long descuento = Long.parseLong(txtDescuento.getText());
        
          //  OPeracion 1 utilizando el valor al detal por cantidad
//            long total = valorProdP * Cantidad;
          // Operacion 2 utilizando el valor al por mayor
            long total = valorProdP_mayor * Cantidad;  
          
            long resultado = total + valorSub;

            long descuento2 = Math.round(resultado * (descuento * 0.01));

            long resultadoDescuento = resultado - descuento2;

            txtSubTotal.setText(String.valueOf(resultado));
            txtTotal_venta.setText(String.valueOf(resultadoDescuento));

            Dventa datos1 = new Dventa();
            Fventa funcion1 = new Fventa();
            datos1.setCod_venta(Integer.parseInt(txtCod_ventaFK.getText()));
            datos1.setTotal_venta(Long.valueOf(txtTotal_venta.getText()));
            funcion1.Total(datos1);
         
            /**
             * ****Quitar Stock*+++++++++
             */
            
           
            Dproducto datos2 = new Dproducto();
            Fproducto funcion2 = new Fproducto();
            int stock = 0;
            int cantidad = 0;
            datos2.setCod_producto(Long.valueOf(txtCod_producto.getText()));
            stock = Integer.parseInt(txtStockDetalle.getText());
            cantidad = Integer.parseInt(txtCantidadProducto.getText());
            stock = stock - cantidad;
            datos2.setStock_producto(stock);
            funcion2.ModificarStockProductos(datos2);
        } else {
            JOptionPane.showMessageDialog(null, "Error Ingreso Producto Form");
        }
        mostrar(txtCod_ventaFK.getText());
        limpiarProductosDetalle();
        txtPrecio_producto.setEditable(false);
        txtCantidadProducto.setText(""); // originalmente es un 1
        cboModoIngreso.setSelectedIndex(0);
        txtCod_producto.setEditable(true);
        txtCod_producto.requestFocus();
        txtCantidadProducto.setEditable(false);   // prueba original es false
        
       

        }
       
        
     /*   
        int Cantidad = Integer.parseInt(txtCantidadProducto.getText());
        long Stock = Long.valueOf(txtStockDetalle.getText());
        if (Cantidad > Stock) {
            JOptionPane.showMessageDialog(null, "La cantidad a vender supera el Stock del producto.");
            txtCantidadProducto.setText("");
            txtCantidadProducto.requestFocus();
            return;
        }
        ;

        Double precio_prod = Double.parseDouble(txtPrecio_producto.getText());
        int cantidad_prod = Integer.parseInt(txtCantidadProducto.getText());
        Double sub_precioC = Double.parseDouble(txtSubPrecioCompra.getText());

        Double resultado1 = precio_prod * cantidad_prod;
        Double resultado2 = cantidad_prod * sub_precioC;

        Ddetalle_venta datos = new Ddetalle_venta();
        Fdetalle_venta funcion = new Fdetalle_venta();

        datos.setCantidad_detalle(Integer.parseInt(txtCantidadProducto.getText()));
        datos.setCod_productoFK(Long.valueOf(txtCod_producto.getText()));
        datos.setPrecio_producto(precio_prod);
        datos.setCod_ventaFK(Integer.parseInt(txtCod_ventaFK.getText()));
        datos.setSubtotal(resultado1);
        datos.setSubPrecioCompra(resultado2);
        datos.setPrecio_compra(Double.parseDouble(txtSubPrecioCompra.getText()));
        

        if (funcion.insertar(datos)) {

            Double valorProdP = Double.parseDouble(txtPrecio_producto.getText());
            Double valorSub = Double.parseDouble(txtSubTotal.getText());

            Double descuento = Double.parseDouble(txtDescuento.getText());

            Double total = valorProdP * Cantidad;
            Double resultado = total + valorSub;

            Double descuento2 = resultado * (descuento * 0.01);

            Double resultadoDescuento = resultado - descuento2;

             
            // FORMATEADOR
            DecimalFormat formateador = new DecimalFormat("0.00");
                    
            txtSubTotal.setText(String.valueOf(formateador.format(resultado)));
            txtTotal_venta.setText(String.valueOf(formateador.format(resultadoDescuento)));

            
            
            Dventa datos1 = new Dventa();
            Fventa funcion1 = new Fventa();
            datos1.setCod_venta(Integer.parseInt(txtCod_ventaFK.getText()));
            datos1.setTotal_venta(Double.valueOf(txtTotal_venta.getText()));
            funcion1.Total(datos1);
        */
            /**
             * ****Quitar Stock*+++++++++
             */
        /*    
            Dproducto datos2 = new Dproducto();
            Fproducto funcion2 = new Fproducto();
            int stock = 0;
            int cantidad = 0;
            datos2.setCod_producto(Long.valueOf(txtCod_producto.getText()));
            stock = Integer.parseInt(txtStockDetalle.getText());
            cantidad = Integer.parseInt(txtCantidadProducto.getText());
            stock = stock - cantidad;
            datos2.setStock_producto(stock);
            funcion2.ModificarStockProductos(datos2);
        } else {
            JOptionPane.showMessageDialog(null, "Error Ingreso Producto Form");
        }
        mostrar(txtCod_ventaFK.getText());
        limpiarProductosDetalle();
        txtPrecio_producto.setEditable(false);
        txtCantidadProducto.setText("1");
        cboModoIngreso.setSelectedIndex(0);
        txtCod_producto.setEditable(true);
        txtCod_producto.requestFocus();
        txtCantidadProducto.setEditable(false);
            
            */
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnQuitarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProductoActionPerformed

//                        boton (-)
         if (!txtCod_detalle.getText().equals("")) {
            Ddetalle_venta datos = new Ddetalle_venta();
            Fdetalle_venta funcion = new Fdetalle_venta();
            datos.setCod_detalle(Integer.parseInt(txtCod_detalle.getText()));
            funcion.eliminar(datos);
            mostrar(txtCod_ventaFK.getText());

            int cantidadProd = Integer.parseInt(txtCantidadProducto.getText());
            long PrecioProd = Long.valueOf(txtPrecio_producto.getText());
            long SubTotal = Long.valueOf(txtSubTotal.getText());

            long descuento = Long.parseLong(txtDescuento.getText());

            long total = PrecioProd * cantidadProd;
            long resultado = SubTotal - total;

            long descuento2 = Math.round(resultado * (descuento * 0.01));
            long resultadoDescuento = resultado - descuento2;

            txtSubTotal.setText(String.valueOf(resultado));
            txtTotal_venta.setText(String.valueOf(resultadoDescuento));

            //txtTotal_venta.setText(String.valueOf(resultadoDescuento));
            Dventa datos1 = new Dventa();
            Fventa funcion1 = new Fventa();
            datos1.setCod_venta(Integer.parseInt(txtCod_ventaFK.getText()));

            datos1.setTotal_venta(Long.valueOf(txtTotal_venta.getText()));
            funcion1.Total(datos1);

            /**
             * ****Aumentar Stock*+++++++++
             */
            Dproducto datos2 = new Dproducto();
            Fproducto funcion2 = new Fproducto();
            int stock2 = 0;
            int cantidad2 = 0;
            datos2.setCod_producto(Long.valueOf(txtCod_producto.getText()));
            stock2 = Integer.parseInt(txtStockDetalle.getText());
            cantidad2 = Integer.parseInt(txtCantidadProducto.getText());
            int totalRestar = stock2 + cantidad2;
            datos2.setStock_producto(totalRestar);
            funcion2.ModificarStockProductos(datos2);

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
        }
        mostrar(txtCod_ventaFK.getText());
        limpiarProductosDetalle();
        txtPrecio_producto.setEditable(false);
        txtCantidadProducto.setEditable(false);
        txtCod_producto.setEditable(true);
        txtCantidadProducto.setText("1");
        txtCod_producto.setText("");
        txtCod_producto.requestFocus();
       /*
        if (!txtCod_detalle.getText().equals("")) {
            Ddetalle_venta datos = new Ddetalle_venta();
            Fdetalle_venta funcion = new Fdetalle_venta();
            datos.setCod_detalle(Integer.parseInt(txtCod_detalle.getText()));
            funcion.eliminar(datos);
            mostrar(txtCod_ventaFK.getText());

            int cantidadProd = Integer.parseInt(txtCantidadProducto.getText());
            Double PrecioProd = Double.valueOf(txtPrecio_producto.getText());
            Double SubTotal = Double.valueOf(txtSubTotal.getText());

            Double descuento = Double.parseDouble(txtDescuento.getText());

            Double total = PrecioProd * cantidadProd;
            Double resultado = SubTotal - total;

            Double descuento2 = resultado * (descuento*0.01);
            Double resultadoDescuento = resultado - descuento2;

             DecimalFormat formateador = new DecimalFormat("0.00");
            txtSubTotal.setText(String.valueOf(formateador.format(resultado)));
            txtTotal_venta.setText(String.valueOf(formateador.format(resultadoDescuento)));
            
            //txtTotal_venta.setText(String.valueOf(resultadoDescuento));

            Dventa datos1 = new Dventa();
            Fventa funcion1 = new Fventa();
            datos1.setCod_venta(Integer.parseInt(txtCod_ventaFK.getText()));

            datos1.setTotal_venta(Double.valueOf(txtTotal_venta.getText()));
            funcion1.Total(datos1);
        */
            /**
             * ****Aumentar Stock*+++++++++
             */
       /*
            Dproducto datos2 = new Dproducto();
            Fproducto funcion2 = new Fproducto();
            int stock2 = 0;
            int cantidad2 = 0;
            datos2.setCod_producto(Long.valueOf(txtCod_producto.getText()));
            stock2 = Integer.parseInt(txtStockDetalle.getText());
            cantidad2 = Integer.parseInt(txtCantidadProducto.getText());
            int totalRestar = stock2 + cantidad2;
            datos2.setStock_producto(totalRestar);
            funcion2.ModificarStockProductos(datos2);

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
        }
        mostrar(txtCod_ventaFK.getText());
        limpiarProductosDetalle();
        txtPrecio_producto.setEditable(false);
        txtCantidadProducto.setEditable(false);
        txtCod_producto.setEditable(true);
        txtCantidadProducto.setText("1");
        txtCod_producto.setText("");
        txtCod_producto.requestFocus();
       */ 
        
    }//GEN-LAST:event_btnQuitarProductoActionPerformed

    private void txtNombre_productoMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_txtNombre_productoMouseWheelMoved

    }//GEN-LAST:event_txtNombre_productoMouseWheelMoved

    private void btnClickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClickActionPerformed
        
        // Venta con Scanner
        seleccionProd();

        if (foco == 1) {
            txtCantidadProducto.requestFocus();
        } else if (foco == 0) {
            txtCod_producto.requestFocus();
            mostrar(txtCod_ventaFK.getText());
        }


    }//GEN-LAST:event_btnClickActionPerformed

    private void txtCod_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCod_productoActionPerformed

        btnClickActionPerformed(evt);
    }//GEN-LAST:event_txtCod_productoActionPerformed

    private void txtCod_productoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCod_productoCaretUpdate

    }//GEN-LAST:event_txtCod_productoCaretUpdate


    private void txtCantidadProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadProductoActionPerformed

    }//GEN-LAST:event_txtCantidadProductoActionPerformed

    private void txtPrecio_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecio_productoActionPerformed

    }//GEN-LAST:event_txtPrecio_productoActionPerformed

    private void txtCod_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCod_productoKeyTyped

        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE)) {
            evt.consume();
        }

    }//GEN-LAST:event_txtCod_productoKeyTyped

    private void cboComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboComprobanteItemStateChanged

    }//GEN-LAST:event_cboComprobanteItemStateChanged

    private void cboComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboComprobanteActionPerformed
        if (cboComprobante.getSelectedItem() == "Factura") {
            NfacturaAtxt();
            txtNumFactura.setEditable(true);

        } else {

            txtNumFactura.setEditable(false);
            txtNumFactura.setText("0");
        }
    }//GEN-LAST:event_cboComprobanteActionPerformed

    
    
    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
      
        
        // validacion vueltos campo vacio
        if (txtImporte.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un Monto ");
            txtImporte.setText("");
            txtImporte.requestFocus();
            return;
        }
        //Fin  validacion vueltos campo vacio
        
        // Villete o Denominacion con que paga el cliente 
        long importe = Long.valueOf(txtImporte.getText()); 
        // suma de subtotales y total a pagar por el cliente
        long total = Long.valueOf(txtTotal_venta.getText());

        if (importe >= total) {
            long cambio = importe - total;
            txtCambio.setText(String.valueOf(cambio)); // Vuelto del cliente
            
            Funciones.FabrirCajon.cajon();
            
            JOptionPane.showMessageDialog(rootPane, "Vuelto : " + cambio);

            Fventa funcion = new Fventa();
            Dventa datos = new Dventa();

            // Villete o Denominacion con que paga el cliente 
            datos.setPago(importe);

            datos.setCod_venta(Integer.parseInt(txtCod_venta.getText()));

            int unidad = cboMetodoPago.getSelectedIndex();
            datos.setMetodo_pago((String) cboMetodoPago.getItemAt(unidad));

            try {
                InetAddress addr = InetAddress.getLocalHost();
                String hostname = addr.getHostName();
                datos.setNomCaja(hostname);
                
                // Deteccion de la ip del equipo

            } catch (Exception e) {
                // TODO: Add catch code
                e.printStackTrace();
            }

            funcion.Pago(datos);

            java.util.Locale locale = new Locale("es", "CL");
            
            int n = JOptionPane.showConfirmDialog(null, "Desea imprimir Tikete ", "Esta seguro.!!!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (n==1) {
                
                 // no
            }else{
                if (cboComprobante.getSelectedItem().equals("Factura")) {
                try {
                
                    int codigo = Integer.parseInt(txtCod_venta.getText());
                    //long pago = Long.parseLong(txtImporte.getText());
                    JasperReport jr = (JasperReport) JRLoader.loadObject(VistaBoleta.class.getResource("/Reportes/RptFactura.jasper"));

                    Map parametro = new HashMap<String, Integer>();
                    parametro.put(JRParameter.REPORT_LOCALE, locale);

                    parametro.put("cod_venta", codigo);

                    JasperPrint jp = JasperFillManager.fillReport(jr, parametro, cn);
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.show();
                
                    // JasperPrintManager.printReport( jp, true);
                } catch (Exception e) {

                    JOptionPane.showMessageDialog(rootPane, "error" + e);
                }
            } else if (cboComprobante.getSelectedItem().equals("Boleta")) {
                try {
                    
                    int codigo = Integer.parseInt(txtCod_venta.getText());

//                   JasperReport jr = (JasperReport) JRLoader.loadObject(VistaBoleta.class.getResource("/Reportes/RptBoleta.jasper"));
                    JasperReport jr = (JasperReport) JRLoader.loadObject(VistaBoleta.class.getResource("/Reportes/RptBoleta_1.jasper"));
                                                          
                    Map parametro = new HashMap<String, Integer>();
                    parametro.put("cod_venta", codigo);
                    parametro.put(JRParameter.REPORT_LOCALE, locale);

                    JasperPrint jp = JasperFillManager.fillReport(jr, parametro, cn);
                    
                    //JasperViewer jv = new JasperViewer(jp, false); 
                   // jv.show();
                
                   // Formulario directo a la impresora
                     JasperPrintManager.printReport( jp, true);
                    
                    
                    btnNuevo.setEnabled(true);
                } catch (Exception e) {

                    JOptionPane.showMessageDialog(rootPane, "error" + e);
                }
                  
               
            }
                
              
            }
            
            

        } else {

            JOptionPane.showMessageDialog(rootPane, "El importe debe ser mayor o igual al total de la venta");
            txtImporte.setText("");
            txtImporte.requestFocus();

        }
        this.setClosable(true);
        
        /*
        DecimalFormat formateador = new DecimalFormat("0.00");
        
        if (txtImporte.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un Monto ");
            txtImporte.setText("");
            txtImporte.requestFocus();
            return;
        }

        Double importe = Double.valueOf(txtImporte.getText());
        Double total = Double.valueOf(txtTotal_venta.getText());

        if (importe >= total) {
            Double cambio = importe - total;
            txtCambio.setText(String.valueOf(formateador.format(cambio)));

            JOptionPane.showMessageDialog(rootPane, "Vuelto : " + formateador.format(cambio));
            Fventa funcion = new Fventa();
            Dventa datos = new Dventa();

            datos.setPago(importe);

            datos.setCod_venta(Integer.parseInt(txtCod_venta.getText()));

            funcion.Pago(datos);

            if (cboComprobante.getSelectedItem().equals("Factura")) {
                try {
                    int codigo = Integer.parseInt(txtCod_venta.getText());
                    //long pago = Long.parseLong(txtImporte.getText());
                    JasperReport jr = (JasperReport) JRLoader.loadObject(VistaBoleta.class.getResource("/Reportes/RptFactura.jasper"));

                    Map parametro = new HashMap<String, Integer>();
                    parametro.put("cod_venta", codigo);

                    JasperPrint jp = JasperFillManager.fillReport(jr, parametro, cn);
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.show();

                    // JasperPrintManager.printReport( jp, true);
                } catch (Exception e) {

                    JOptionPane.showMessageDialog(rootPane, "error" + e);
                }
            } else if (cboComprobante.getSelectedItem().equals("Boleta")) {
                try {
                    int codigo = Integer.parseInt(txtCod_venta.getText());

                    JasperReport jr = (JasperReport) JRLoader.loadObject(VistaBoleta.class.getResource("/Reportes/RptBoleta.jasper"));

                    Map parametro = new HashMap<String, Integer>();
                    parametro.put("cod_venta", codigo);

                    JasperPrint jp = JasperFillManager.fillReport(jr, parametro, cn);
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.show();

                    // JasperPrintManager.printReport( jp, true);
                    btnNuevo.setEnabled(true);
                } catch (Exception e) {

                    JOptionPane.showMessageDialog(rootPane, "error" + e);
                }

            }

        } else {

            JOptionPane.showMessageDialog(rootPane, "El importe debe ser mayor o igual al total de la venta");
            txtImporte.setText("");
            txtImporte.requestFocus();

        }
        this.setClosable(true);
      */
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

         Dventa datos = new Dventa();
        Fventa funcion = new Fventa();
        Fdetalle_venta funcion2 = new Fdetalle_venta();

        datos.setCod_venta(Integer.parseInt(txtCod_venta.getText()));
        String importe0 = txtImporte.getText();

        if ("".equals(importe0)) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor en el IMPORTE");
            txtImporte.requestFocus();
            return;
        }

        long importe = Long.parseLong(txtImporte.getText());

        funcion2.mostrar(txtCod_ventaFK.getText());

        if (funcion2.totalRegistros == 0) {
            funcion.eliminar(datos);
            JOptionPane.showMessageDialog(null, "Venta eliminada ya que no posee registros");
        } else if (importe == 0) {
            JOptionPane.showMessageDialog(null, "Realizo una venta sin INGRESAR IMPORTE");
            txtImporte.requestFocus();
            return;
        }

        this.setClosable(true);
        txtImporte.setText("0");
        txtImporte.setEditable(true);

        txtDescuento.setText("0");
        txtDescuento.setEditable(true);
        
        txtDescuentoXP.setText("0");
        txtDescuentoXP.setEditable(false);
        
        txtNumFactura.setText("");
        txtStockDetalle.setText("");
        txtCod_venta.setText("");
        txtCod_ventaFK.setText("");
        txtCod_detalle.setText("");

        txtTotal_venta.setText("0");
        txtSubTotal.setText("0");
        txtCambio.setText("0");
        txtCod_producto.setText("");
        txtCod_producto.setEditable(false);
        txtNombre_producto.setText("");
        txtPrecio_producto.setText("");
        txtCantidadProducto.setText("");
        btnGuardar.setEnabled(true);
        txtNumFactura.setText("");
        btnNuevo.setEnabled(false);

        txtNombre_cliente.setText("Cliente General");
        txtCod_cliente.setText("2");

        cboComprobante.setSelectedIndex(0);
        mostrar("0");

        btnQuitarProducto.setEnabled(false);
        btnbuscarProducto.setEnabled(false);
        btnAgregarProducto.setEnabled(false);
        btnCalcular.setEnabled(false);
        
        /*
        Dventa datos = new Dventa();
        Fventa funcion = new Fventa();
        Fdetalle_venta funcion2 = new Fdetalle_venta();

        datos.setCod_venta(Integer.parseInt(txtCod_venta.getText()));
        String importe0 = txtImporte.getText();

        if ("".equals(importe0)) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor en el IMPORTE");
            txtImporte.requestFocus();
            return;
        }

        Double importe = Double.parseDouble(txtImporte.getText());

        funcion2.mostrar(txtCod_ventaFK.getText());

        if (funcion2.totalRegistros == 0) {
            funcion.eliminar(datos);
            JOptionPane.showMessageDialog(null, "Venta eliminada ya que no posee registros");
        } else if (importe == 0) {
            JOptionPane.showMessageDialog(null, "Realizo una venta sin INGRESAR IMPORTE");
            txtImporte.requestFocus();
            return;
        }

        this.setClosable(true);
        txtImporte.setText("0");
        txtImporte.setEditable(true);

        txtDescuento.setText("0");
        txtDescuento.setEditable(true);
        txtNumFactura.setText("");
        txtStockDetalle.setText("");
        txtCod_venta.setText("");
        txtCod_ventaFK.setText("");
        txtCod_detalle.setText("");

        txtTotal_venta.setText("0");
        txtSubTotal.setText("0");
        txtCambio.setText("0");
        txtCod_producto.setText("");
        txtCod_producto.setEditable(false);
        txtNombre_producto.setText("");
        txtPrecio_producto.setText("");
        txtCantidadProducto.setText("");
        btnGuardar.setEnabled(true);
        txtNumFactura.setText("");
        btnNuevo.setEnabled(false);

        txtNombre_cliente.setText("Cliente General");
        txtCod_cliente.setText("2");

        cboComprobante.setSelectedIndex(0);
        mostrar("0");

        btnQuitarProducto.setEnabled(false);
        btnbuscarProducto.setEnabled(false);
        btnAgregarProducto.setEnabled(false);
        btnCalcular.setEnabled(false);
        */

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        FrmVistacliente form = new FrmVistacliente();
        Comprueba = 2;
        deskPricipal.add(form);
        //   form.setClosable(true);
        form.setIconifiable(true);
        form.setMaximizable(false);
        form.toFront();
        form.setVisible(true);

    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void cboModoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboModoIngresoActionPerformed

         if (cboModoIngreso.getSelectedItem() == "x Mayor") {
            txtCantidadProducto.setText("1");
            txtCantidadProducto.setEditable(false);
            txtNombre_producto.setEditable(false);
            txtStockDetalle.setEditable(false);
            txtPrecio_producto.setEditable(false);
            txtCod_producto.setEditable(true);
            txtCod_producto.requestFocus();
        } else if (cboModoIngreso.getSelectedItem() == "x Detal") {
            txtCantidadProducto.setText("1"); // prueba
            txtCod_producto.setEditable(true);
            txtCantidadProducto.setEditable(false);
            txtNombre_producto.setEditable(false);
            txtStockDetalle.setEditable(false);
            txtPrecio_producto.setEditable(false);
            txtCod_producto.requestFocus();
        }
        /*
        if (cboModoIngreso.getSelectedItem() == "x Mayor") {
            txtCantidadProducto.setText("0");
            txtCantidadProducto.setEditable(false);
            txtNombre_producto.setEditable(false);
            txtStockDetalle.setEditable(false);
            txtPrecio_producto.setEditable(false);
            txtCod_producto.setEditable(true);
            txtCod_producto.requestFocus();
        } else if (cboModoIngreso.getSelectedItem() == "x Unidad") {
            txtCantidadProducto.setText("1");
            txtCod_producto.setEditable(true);
            txtCantidadProducto.setEditable(false);
            txtNombre_producto.setEditable(false);
            txtStockDetalle.setEditable(false);
            txtPrecio_producto.setEditable(false);
            txtCod_producto.requestFocus();
        }
        */
    }//GEN-LAST:event_cboModoIngresoActionPerformed

    private void txtCantidadProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadProductoKeyTyped

    private void txtNumFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumFacturaKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumFacturaKeyTyped

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDescuentoKeyTyped

    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE)
                && (c != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtImporteKeyTyped

    private void txtNombre_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre_productoActionPerformed

    }//GEN-LAST:event_txtNombre_productoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmVentaDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVentaDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVentaDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVentaDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>  
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVentaDetalle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnClick;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitarProducto;
    private javax.swing.JButton btnbuscarProducto;
    private javax.swing.JComboBox<String> cboComprobante;
    private javax.swing.JComboBox<String> cboMetodoPago;
    private javax.swing.JComboBox<String> cboModoIngreso;
    private com.toedter.calendar.JDateChooser dcFecha_venta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTabla;
    private javax.swing.JLabel lblModo;
    private javax.swing.JTextField txtCambio;
    public static javax.swing.JTextField txtCantidadProducto;
    public static javax.swing.JTextField txtCod_cliente;
    private javax.swing.JTextField txtCod_detalle;
    public static javax.swing.JTextField txtCod_producto;
    public static javax.swing.JTextField txtCod_usuario;
    private javax.swing.JTextField txtCod_venta;
    private javax.swing.JTextField txtCod_ventaFK;
    private javax.swing.JTextField txtDescuento;
    public static javax.swing.JTextField txtDescuentoXP;
    private javax.swing.JTextField txtImporte;
    public static javax.swing.JTextField txtNombre_cliente;
    public static javax.swing.JTextField txtNombre_producto;
    public static javax.swing.JLabel txtNombre_usuario;
    private javax.swing.JTextField txtNumFactura;
    public static javax.swing.JTextField txtPrecio_Prod_Mayor;
    public static javax.swing.JTextField txtPrecio_producto;
    public static javax.swing.JTextField txtStockDetalle;
    public static javax.swing.JTextField txtSubPrecioCompra;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal_venta;
    // End of variables declaration//GEN-END:variables

    
}
