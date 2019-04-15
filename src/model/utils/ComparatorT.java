package model.utils;

public interface ComparatorT<T> {

	/**
	 * Compara dos objetos para saber de que manera organizarlos.
	 * @param objI Primer objeto que se va a comparar por algun criterio.
	 * @param objD Segundo objeto que se va a comparar por algun criterio.
	 * @return 1 Si el primer objeto es mayor que el segundo.
	 * 		  -1 Si el primer objeto es menor que el segundo.
	 * 		   0 Si los dos objetos son iguales.
	 */
	public int compare( T objI, T objD);
}
