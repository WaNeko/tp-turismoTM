package turismoTierraMedia.producto;

public class Atraccion extends Producto {
	
	public static Atraccion crear(String construct) {
		Atraccion newer = new Atraccion();
        newer.mutar(construct);
        return newer;
    }
	
}