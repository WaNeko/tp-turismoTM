package turismoTierraMedia.ownTools;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class Archivo {
	
	public static final String DIRECTORIO = "C:\\Users\\User\\Documents\\Programacion\\Archivos\\";
	
	public static String cargar(String nombreArchivo) {
		String valor = "";
		
	        try {
	            //@-set-
	                String linea = "";
	                FileReader fs = new FileReader(DIRECTORIO + nombreArchivo);
	                BufferedReader reader = new BufferedReader(fs);        
	            while((linea = reader.readLine()) != null)
	                valor += linea + ";";
	            reader.close(); 
	        } catch(IOException e) {
	            e.printStackTrace(); 
	        }
		return valor;
	}
	
	
	public static void salvar(String nombreArchivo, String Escribe) {
        //@-set-
            String[] valor = Escribe.split(";"); 
        try {
            //@-set-
                BufferedWriter writer = new BufferedWriter(new FileWriter(DIRECTORIO + nombreArchivo));
            for(int i = 0; i < valor.length; i++)
                writer.write(valor[i] + "\n");
            writer.close(); 
        } catch(IOException e) {
            e.printStackTrace(); 
        }
    }

	public static void aniadir(String nombreArchivo, String Escribe) {
		salvar(nombreArchivo, cargar(nombreArchivo) + Escribe);
	}
	
}
