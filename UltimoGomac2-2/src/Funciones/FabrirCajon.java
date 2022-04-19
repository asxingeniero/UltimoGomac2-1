package Funciones;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class FabrirCajon {
  
    public static void cajon(){
        
        String code1 = ""+(char)27+(char)112+(char)0+(char)10+(char)100; //decimal he probado: 27 112 0 150 250 - 271120150250 - 27:112:0:150:250 ///27 112 0 150 250
          //  int dos[]= 27,112,0,2,0;
		String code2 = "1B 70 0 96 FA"; //  hexadecimal: 1B:70:0:96:FA -  0x1B70096FA - 0x1B 70 0 96 FA - 1B70096FA
		String code = "ESCp0û."; //Ascii idem
	
		 PrintService service = PrintServiceLookup.lookupDefaultPrintService();
		// System.out.println(service.getName()); //la impresora esta bien
		 DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		DocPrintJob pj = service.createPrintJob();
		 byte[] bytes;
		 bytes=code1.getBytes();
		 Doc doc= new SimpleDoc(bytes,flavor,null);
		  try {
			pj.print(doc, null);
                        
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
    
     
    
    
    
}
