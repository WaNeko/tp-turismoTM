package turismoTierraMedia.producto;

public class Producto {
	

	
	//@-props-
		protected String
			nombre,
			tipo;
		
		protected int 
			cupo,
			costo;
		
		protected double
			horas;

    //#region @-setter and getters-
        //@-setter-
            public void setNombre(String nombre) {
                this.nombre = nombre; 
            }

            public void setTipo(String tipo) {
                this.tipo = tipo;
            }

            public void setCosto(int costo) {
                this.costo = costo;
            }

            public void setHoras(double horas) {
                this.horas = horas;
            }

            public void setCupo(int cupo) {
                this.cupo = cupo;
            }
        //@-setter-
            public String getNombre() {
                return this.nombre;
            }
            public String getTipo() {
                return this.tipo;
            }
            public int getCosto() {
                return costo;
            }
            public double getHoras() {
                return this.horas;
            }
                        
            public int getCupo() {
                return cupo;
            }
    //#endregion

    public Producto mutar(String configuracion) {
    	if(configuracion == null || configuracion == "") return this;
        String[] config = configuracion.split(", ");
		for(String claveValor:config) {
			String[] claveYValor = claveValor.split(":");
			switch (claveYValor[0]) {
				case "nombre":
                    this.setNombre(claveYValor[1]);
					break;
				case "tipo":
                    this.setTipo(claveYValor[1]);
					break;
				case "costo":
					int costo = Integer.parseInt(claveYValor[1]);
                    this.setCosto(costo);
					break;
				case "cupo":
                    int cupo = Integer.parseInt(claveYValor[1]);
                    this.setCupo(cupo);
					break;
				case "horas":
                    double horas = Double.parseDouble(claveYValor[1]);
                    this.setHoras(horas);
                    break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + claveYValor[0]);
			}
		}
        return this;
    }

    public String toString() {
        return getNombre() + ":{ tipo:" + getTipo() + " costo:"+ getCosto() + ", cupo:" + getCupo() + ", horas:"+ getHoras() + " }";
    }

	public static Producto crear(String construct) {
        Producto newer = new Producto();
        newer.mutar(construct);
        return newer;
    }
}  