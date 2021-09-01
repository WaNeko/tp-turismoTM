package turismoTierraMedia.ownTools;

import java.util.function.Function;
import turismoTierraMedia.producto.*;


public interface MyArray {
	
	public static Producto[] concat(Producto[] original, Producto[] aUnir) {
		
		Producto[] resultado = new Producto[original.length + aUnir.length];
	
		for (int i = 0; i < original.length; i++) {
			resultado[i] = original[i];
		}
		int pos = 0;
		for (int i = original.length; i < resultado.length; i++) {
			resultado[i] = aUnir[pos];
			pos++;
		}
		return resultado;

	}

	public static Producto[] filtrar(Producto[] atrs, Function<Producto, Boolean> tester) {

		Producto[] resultado;
		int largo = 0;
		int pos = 0;
		for (Producto i : atrs)
			if (tester.apply(i))
				largo++;
		resultado = new Producto[largo];
		for (Producto i : atrs) {
			if (tester.apply(i)) { resultado[pos] = i; pos++; }
		}
		return resultado;

	}
	//Verifica que las atracciones no sean iguales
	public static boolean someEquals(Producto[] atrs, Producto atr) {
		boolean igual = false;
		for (Producto e : atrs)
			if (e != null)
				if (e.equals(atr))
					igual = true;
		return igual;
	}
	//Ordena las atracciones de mayor a menor precio, usa someEquals
	public static interface Ordenar {

		public static Producto[] mayorPrecio(Producto[] atrs) {
			Producto[] resultado = new Producto[atrs.length];
			for (int i = 0; i < atrs.length; i++) {
				Producto max = Atraccion.crear("nombre:_, tipo:_, costo:0, cupo:0, horas:0.0");
				for (Producto j : atrs)
					if (j.getCosto() > max.getCosto() && !someEquals(resultado, j))
						max = j;
				resultado[i] = max;
			}
			return resultado;
		}
		
		public static Producto[] mayorHora(Producto[] atrs) {
			Producto[] resultado = new Producto[atrs.length];
			for (int i = 0; i < atrs.length; i++) {
				Producto max = Atraccion.crear("nombre:_, tipo:_, costo:1000, cupo:1000, horas:0.0");
				for (Producto j : atrs)
					if (max.getHoras() <= j.getHoras() && !someEquals(resultado, j))
						max = j;
				resultado[i] = max;
			}
			return resultado;
		}

	}
	
}
