package compromisosLupita;


public class seguimiento extends javax.swing.JFrame {
    /*
                    AGENDA
    01-05-2021
                        Avances
    Venta
    1.Scanner + Detal + Descuento = funcionando
    2.Scanner + Mayor             = funcionando
    3.Lupita + Detal + Descuento  = funcionando
    4.Lupita + Mayor              = funcionando
    
    Reportes
    Los reportes se deben corregir los valores
    
    05-05-2021
                            Actividad
    
    1. en el boton (-) del formulario de FrmVenta_detalle
    esta tomando el precio del producto para sumarlo al stock 
    del producto
    
    
    03-05-2021
                          Actividad
    8. La funcion que regresa los articulos del stock en el boton ( - )
    esta tomando de la columna precio para realizar la suma . 11.05 pm
    7. Cambiar el color de fondo de las pantallas       *
    
    
    
    1. ingresar campo descuentoxp en la tabla detalle venta   *
    2. ingresar campo precioxp en la tabla detalle venta      *
  3.  implementar getters y setters en clase Ddetalle_venta para descuento_xp  *
    4. actualizar consultas en la clase Fdetalle_venta
    
    5. implementar getters y setters en clase Ddetalle_venta para precio_xm  *
    6. actualizar consultas en la clase Fdetalle_venta
    
    02-05-2021
                          Actividad
    1. test de las diferentes funcionalidades del sistema
    
    
    01-05-2021
                               Actividad:
    Activar el descuento por productos en el sistema
    
    1. El campo descuento3 de la tabla venta debe llevarse al getter y setter
    de la clase Dventa.
    
    
    
    26-04-2021
    El campo de texto txtStockDetalle de hay esta tomando el stock de la
    base de datos
    
    
    25-04-2021
    Cuando se realiza la venta en la base de datos no se realiza el descuento
    si no que se le suma la cantidad restante de la resta entre 
    el stock actual menos la cantidad vendida.
    
    El boton que (-) que retira los productos esta presentando inconvenientes
    
    Aumentar el largo de la ventana que contiene el formulario de la lista
    de productos para seleccionarlos y llevarlos al FrmVentaDetalle
    
    al dar clic en el boton iniciar se borran todos los campos pero el campo
    cantidad se coloca en 1.
    ademas en el campo de texto para que el usuario coloque el precion de venta
    al por mayor se coloca una cantidad que no deberia estar.
    
    
    24-04-2021
    link para ingreso de productos
    https://www.tiendadelabelleza.co/producto-categoria/maquillaje/rostro/bases/
    
    1. cambiar X UNIDAD A X DETAL en el boton de modo de venta en el 
    formulario de venta detalle.  **
    
    2. El campo Stock en la tabla de la vista donde se seleccionan los
    productos debe mostrar la cantidad que tiene cada producto.
    
    3. Habilitar descuento unitario por porcentaje.
    
    4. Los colores elejidos deben ser 
    4:1 fondo blanco
    4:2 letras negras
    
    5. Cambiar el color de los campos de texto de fondo negro a
    fondo blanco con letras negras.
    
    5. En el combobox donde se le asigna el acceso a los usuarios que queda  
    en el formulario de gestion de usuarios para el sistema. debe decir 
    5.1 Administrador
    5.2 Vendedor
    
    6. En el formulario de vista detalle al realizar la venta los campos del 
    formulario se limpian, pero el campo cantidad se setea con 1
    
    7. Desabilitar el campo precio mayor cuando la venta no este habilitada
    
    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    22-04-2021
    1. Verificar si el combobox   cboComprobante se esta llenando correctamente 
    de la base de datos
       
    
    $ la base de datos se le añadio el campo precio_venta_mayor a la tabla producto
       AGENDA
    Actividades a realizar el 21 - 04 - 2021
    ------------------------------------------
    1. el sistema presenta errores en el importe de librerias como
    1.1 iReport
    1.2 
    
    
    
    Actividades realizadas el 20 - 04-021
    _-----------_____-------_____-----------
    1) Habilitar en el registro de los productos que ademas 
    del precio del producto pida un precio al por mayor
    
    archivos asociados
    1.1 
    Formulario FrmProducto en carpeta controlador
    clase Dproducto en carpeta Datos
    clase Fproducto en carpeta Funciones
    
    1.1.1 Ingresar el campo precio_venta_mayor en FrmProducto
    1.1.2 Insercion en la base de datos del producto incluyendo el campo 
    precio de la venta al por mayor
    1.1.3 En la clase Dproductos ingresar el atributo precio venta mayor
    1.1.4 En la clase Fproducto 
    
    2. Hacer que el precio ingresado en la base de datos se refleje en el 
       formulario cuando se de clic en la tabla con el listado de productos
    
    2.1 Archivos asociados
    
    2.1.1 FrmProducto
    
    Observaciones los metodos limpira las celdas tambien deben ser 
    tenidos en cuenta para limpiar los campos correspondientes
    
    
    
    int modoVenta = cboModoIngreso;
     
    
    
    
    
    */
    
    // Aqui empieza descuento por producto
//            long descuento3 = Long.parseLong(txtDescuentoXP.getText());
            
            
            // descuento x producto
//            long porcentaje = ((valorProdP * descuento3)/100)* cantidad_prod;
                                  
//            long descuento4 = Math.round(valorProdP * cantidad_prod) - porcentaje;                      
             // pasar el descuento a el campo de texto del total
            
             
//            long total = descuento4;
//            long resultado = total + valorSub;
    
    
    
    public seguimiento() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Paleta rosada");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(seguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(seguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(seguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(seguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new seguimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
