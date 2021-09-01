package turismoTierraMedia.producto;

public class PromoPercentual extends Promocion {

    public PromoPercentual() {
        super();
    }

    public PromoPercentual mutar(String configuracion, Atraccion[] font) {
    	if(configuracion == null || configuracion == "" || font == null) return this;
    	String[] config = configuracion.split(", ");
		int descuento = 0;
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
                case "incluye":
                    String[] nombres = claveYValor[1].split(",");
                    setPromo(new Atraccion[nombres.length]);
                    for(int pos = 0; pos < nombres.length; pos++) {
                    	for(Atraccion i:font) {
                        	if(i.getNombre().equals(nombres[pos])) {
                            	setHoras(getHoras() + i.getHoras());
                            	setCosto(getCosto() + i.getCosto());
                            	setCupo((i.getCupo() > getCupo())? i.getCupo(): getCupo());
                            	getPromo()[pos] = i;
                        	}
                    	}
                    }
                    break;
                case "descuento":
					descuento = Integer.parseInt(claveYValor[1]);
                    break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + claveYValor[0]);
            }
		}
		setCosto(getCosto() * (100 - descuento) / 100 );
        return this;
    }

    public String toString() {
        String incluye = "";
        if(getPromo() != null) {
            for(int i = 0; i < getPromo().length; i++) {
                if(getPromo()[i] != null)
                incluye += getPromo()[i].getNombre() + ((i == (getPromo().length - 1))? "":",");
            }
        }
        return getNombre() + ":{ tipo:" + getTipo() + " costo:"+ getCosto() + ", cupo:" + getCupo() + ", horas:"+ getHoras() + ", incluye:" + incluye +" }";
    }

    public static PromoPercentual crear(String construct, Atraccion[] font) {
        PromoPercentual newer = new PromoPercentual();
        newer.mutar(construct, font);
        return newer;
    }

}
