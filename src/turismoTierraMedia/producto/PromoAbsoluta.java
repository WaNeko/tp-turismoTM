package turismoTierraMedia.producto;

public class PromoAbsoluta extends Promocion{

	public PromoAbsoluta() {
		super();
	}

    public String toString() {
        String incluye = "";
        if(getPromo() != null) {
            for(int i = 0; i < getPromo().length; i++) {
                incluye += getPromo()[i].getNombre() + ((i == (getPromo().length - 1))? "":",");
            }
        }
        return getNombre() + ":{ tipo:" + getTipo() + " costo:"+ getCosto() + ", cupo:" + getCupo() + ", horas:"+ getHoras() + ", incluye:" + incluye +" }";
    }
	
    public static PromoAbsoluta crear(String construct, Atraccion[] font) {
        PromoAbsoluta newer = new PromoAbsoluta();
        newer.mutar(construct, font);
        return newer;
    }

}
