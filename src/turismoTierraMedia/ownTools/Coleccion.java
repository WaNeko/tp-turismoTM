package turismoTierraMedia.ownTools;

import java.util.function.Function;
import turismoTierraMedia.producto.*;

public interface Coleccion {

	//@predicado: funcion que toma algo como argumento y devuelve un valor booleano : (arg) -> { return arg > 1 };

	//@funciones
		//@alguno ejecuta el predicado en cada elemento de la lista , devuelve true si el predicado devuelve true al menos una vez : alguno(new int[] {1,4,3}, elemento -> elemento > 3) >>> true; , porque el 4 cumple la condición;
		//@unir concatena dos arreglos : conCATenar(new int{1, 2, 3}, new int[] {3, 2, 1}); >>> int[] {1, 2, 3, 3, 2, 1};
		//@filtro ejecuta el predicado en cada elemento de la lista pasandole el elemento como argumento, si devuelve false descarta el elemento caso contrario lo mantiene : filtrar(new int[] {10, 4, 2, 9, 2, 1, 0}, elemento -> elemento < 6); >>> int[] {4, 2, 2, 1, 0};
		//@orden Ordena un arreglo comparando dos elementos 
	
	public static Producto[] unir(Producto[] original, Producto[] aUnir) {
		Producto[] resultado = new Producto[original.length + aUnir.length];
		for (int i = 0; i < original.length; i++)
			resultado[i] = original[i];
		for (int i = original.length; i < resultado.length; i++) 
			resultado[i] = aUnir[i - original.length];
		return resultado;
	} 
	public static Producto[] filtro(Producto[] lista, Predicate<Producto> tester) {
		int largo = 0, pos = 0;
		for (Producto i : lista)
			largo += (tester.apply(i))? 1 : 0;
		Producto[] resultado = new Producto[largo];
		for (Producto i : lista)
			if (tester.apply(i)) 
				resultado[pos++] = i;
		return resultado;
	} 
	public static boolean alguno(Producto[] lista, Predicate<Producto> callback) {
		boolean unoCumpleLaCondicion = false;
		for(Producto elemento:lista)
			unoCumpleLaCondicion |= callback.apply(elemento);
		return unoCumpleLaCondicion;
	} 
	public static Producto[] orden(Producto[] lista, Function<Producto, Producto, Integer> callback) {
		Producto[] aOrdenar = lista;
		for(Producto elemento:lista) {
			Producto[] nuevo = new Producto[aOrdenar.length];
			for(int i = 0; i < aOrdenar.length - 1; i++) {
				int callbackValue = callback.apply(aOrdenar[i], aOrdenar[i + 1]);
				if(callbackValue == 1) {
					nuevo[i] = aOrdenar[i + 1];
					nuevo[i + 1] = aOrdenar[i];
				} else if(callbackValue == -1 || callbackValue == 0) {
					nuevo[i] = aOrdenar[i];
					nuevo[i + 1] = aOrdenar[i + 1];
				} else throw new Error("solo se admite un número entre -1 y 1");
			}
			aOrdenar = nuevo;
		}
		return aOrdenar;
	}
}
