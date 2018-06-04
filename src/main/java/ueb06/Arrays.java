package ueb06;


class Arrays {
	/**
	 * Gibt eine Stringrepraesentation des Arrays zurueck, z.B. [] oder [1, 2, 3]
	 */
	static <T> String toString(T[] array) {
		if(array.length == 0){
			return "[]";
		}else{
			return "[" + toString(array) + "]";
		}
	}

	/**
	 * Gib zurück, ob ein Objekt in dem Array enthalten ist; verwendet `equals`
	 */
	static <T> boolean contains(T[] array, T object) {
		if (array.length == 0) {
			return false;
		} else if(array[0].equals(object)){
			return true;
		}else {
			return contains(java.util.Arrays.copyOfRange(array, 1, array.length), object);
		}

	}
}
