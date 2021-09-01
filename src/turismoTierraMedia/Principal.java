package turismoTierraMedia;

import java.util.Scanner;
import turismoTierraMedia.ownTools.Archivo;
import turismoTierraMedia.ownTools.*;
import turismoTierraMedia.producto.*;

public class Principal implements MyArray {
	
    public static void main(String[] args) {

    	
    	//@sets
    		Persona[] personas = cargarPersona("Personas.txt");
    		Atraccion[] atracciones = cargarAtraccion("Atraccion.txt");
    		Promocion[] promociones = cargarPromocion("Promocion.txt", atracciones);
    		Producto[] aOfrecer = MyArray.concat(atracciones, promociones);

		for(Persona p:personas) {// Reordena el array de sugerencias conforme la preferencia del usuario
			aOfrecer = reAcomodar(aOfrecer, p.getPreferencia()); 
	        System.out.println(p);
	        generarItinerario(aOfrecer, p);
	        guardarItinerario(p);
		}		
    }
    
    public static Atraccion[] cargarAtraccion(String ArchivoAtracciones) { // Carga Archivo Atracciones
    	String[] construyeAtracciones = Archivo.cargar(ArchivoAtracciones).split(";");
    	Atraccion[] atracciones = new Atraccion[construyeAtracciones.length];// Array vacio tamaño de los espacios generados por split 
    	for(int i = 0; i < construyeAtracciones.length; i++) {
    		atracciones[i] = Atraccion.crear(construyeAtracciones[i]);// Crea las  atracciones que hay en el archivo
		}
    	return atracciones; //Devuelve array con las atracciones
    }
    
    public static Persona[] cargarPersona(String lugar) {// Carga Archivo Personas
    	String[] cons = Archivo.cargar(lugar).split(";");
    	Persona[] personas = new Persona[cons.length]; 
    	for(int i = 0; i < cons.length; i++) {
    		personas[i] = Persona.crear(cons[i]);
		}
    	return personas;//Devuelve array con los usuarios
    }
    
    public static Promocion[] cargarPromocion(String archivoPromociones, Atraccion[] fuente) {
    	String[] construct = Archivo.cargar(archivoPromociones).split(";");
    	Promocion[] promociones = new Promocion[construct.length]; 
    	for(int i = 0; i < promociones.length; i++) {
			String tipo = "";// Se refiere al tipo de la promocion
    		for(String strs:construct[i].split(", "))
    			tipo = (strs.split(":")[0].equals("descuento"))? "percentual": (strs.split(":")[0].equals("gratis"))? "AxB" : tipo;
    		promociones[i] = (tipo == "AxB")? PromocionAxB.crear(construct[i], fuente)
				:(tipo == "percentual")? PromoPercentual.crear(construct[i], fuente) 
    			: PromoAbsoluta.crear(construct[i], fuente);
    	}
    	return promociones;// Devuelve array con las promociones
    }

    public static Producto[] reAcomodar(Producto[] aOrdenar, String tipo) {
    
    	Producto[] nuevo = Ordenar.mayorPrecio(Ordenar.mayorHora(aOrdenar));
		nuevo = MyArray.concat(
			MyArray.filtrar(nuevo, e -> !Atraccion.class.isInstance(e)),
			MyArray.filtrar(nuevo, e -> Atraccion.class.isInstance(e))); //Ordena las promociones primero y despues las atracciones
    	nuevo = MyArray.concat(
			MyArray.filtrar(nuevo, e -> e.getTipo().equals(tipo)), 
			MyArray.filtrar(nuevo, e -> !e.getTipo().equals(tipo))); //Ordena por la preferencia primero y despues el resto
    	return nuevo;
    	
    }
    
    public static void generarItinerario(Producto[] aOfrecer, Persona p) {// Metodo que genera itinerario del usuario
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
		System.out.println("~~~~");
		for(Producto i:aOfrecer) {
				boolean meAlcanza = p.getPresupuesto() >= i.getCosto();
				boolean tengoTiempo = p.getHoras() >= i.getHoras();
				boolean hayEspacio = i.getCupo() > 0;
				boolean loCompreYa = false;
			
			if(Atraccion.class.isInstance(i)) {
				for(Object j:p.itinerario.toArray()) {// 
					loCompreYa |= ((Producto) j).getNombre().equals(i.getNombre());//  Verifica si la atraccion no fue comprada 
				}
			} else {
				for(Object j:p.itinerario.toArray()) {
					for(Atraccion k:((Promocion)i).getPromo())
						loCompreYa |= k.getNombre().equals(((Atraccion) j).getNombre());// Verifica si las Atracciones de la promocion no fueron compradas
				}
			}
			
	      if(meAlcanza && tengoTiempo && hayEspacio && !loCompreYa){
	            System.out.print("comprar " + i + " ?(Y/N): ");
	            String responce = sc.next();
	            if(responce.equals("Y")) {
	            	p.comprar(i);
	            	i.setCupo(i.getCupo() - 1);
	            	if(Promocion.class.isInstance(i)) // si es una promocion le descuenta un cupo a cada atraccion  
	            		for(Atraccion j:((Promocion) i).getPromo()) 
	            			j.setCupo(j.getCupo() - 1);
	            }
       		}
		}
		System.out.println("~~~~");
		//for(Producto i:aOfrecer) System.out.println(i);
		//System.out.println("~~~~");
    }
    
    public static void guardarItinerario(Persona p) {
    	
		Archivo.salvar(p.getNombre() + "_itinerario.txt", p + " [");
		p.itinerario.forEach(e -> {
			Archivo.aniadir(p.getNombre() + "_itinerario.txt", "; " + e.getNombre() + ",");
		});
		Archivo.aniadir(p.getNombre() + "_itinerario.txt", " ]");
    	
    }
}
