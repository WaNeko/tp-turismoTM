package turismoTierraMedia;

import java.util.ArrayList;
import turismoTierraMedia.producto.*;

public class Persona {
	
	public static void main(String[] args) {
		
		Atraccion a1 = Atraccion.crear(null);
		Promocion pr1 = Promocion.crear(null, null);
		
		System.out.println(Promocion.class.isInstance(pr1));
		System.out.println(pr1);
		System.out.println(a1);
		System.out.println(Promocion.class.isInstance(a1));
		
	}

    //@-props-
        private String 
            preferencia,
            nombre ; 

        private int 
            presupuesto;

        private double
            horas; 

        public ArrayList<Atraccion> 
            itinerario = new ArrayList<Atraccion>();


    protected Persona(String nombre, String preferencia, int presupuesto, float horas) {
        this.preferencia = preferencia;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.horas = horas;
    }

    //#region @setters and getters
        //@set
            public String setNombre(String n) {
                this.nombre = n; 
                return nombre;
            }
            public String setPreferencia(String p) {
                this.preferencia = p;
                return preferencia;
            }
            public int setPresupuesto(int p) {
                this.presupuesto = p;
                return presupuesto;
            }
            public double setHoras(double h) {
                this.horas = h;
                return horas;
            }


        //@get
            public String getNombre() {
                return this.nombre;
            }
            public String getPreferencia() {
                return this.preferencia;
            }
            public int getPresupuesto() {
                return presupuesto;
            }
            public double getHoras() {
                return this.horas;
            }   
    //#endregion
        
    public void comprar(Producto prd) {
		setPresupuesto(getPresupuesto() - prd.getCosto());
		setHoras(getHoras() - prd.getHoras());
    	if(Atraccion.class.isInstance(prd)) {// Verifica si es una atraccion
    		this.itinerario.add((Atraccion) prd);
    	} else if(Promocion.class.isInstance(prd)) {//Verifica si es una Promocion
    		for(Atraccion i:((Promocion) prd).getPromo())
    			this.itinerario.add(i);
    	} else throw new Error("O noooo");
    }


    @Override
    public String toString() {
        return getNombre() + ":{ preferencia:" + getPreferencia() + ", presupuesto:" + getPresupuesto() + ", horas:" + getHoras() + " };";
    }

    public static Persona crear(String construct) {
        String[] s = construct.split(" ");

        int s_2 = Integer.parseInt(s[2]);
        float s_3 = Float.parseFloat(s[3]);

        return new Persona(s[0], s[1], s_2, s_3);
    }

}