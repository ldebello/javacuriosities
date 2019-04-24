package ar.com.javacuriosities.collections.elements;

import java.util.Objects;

public class MapKey {

    private String key;

    public MapKey(String key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.key != null ? this.key.hashCode() : 0);
        return hash;
    }

    /*
     * Dejarlo comentado, hasta que corremos el ejemplo
     * y vemos como que acepta duplicados, después descomentarlo y volver a correr
     * MapExamples.java.
     *
     *
     * El contrato de equals dice que si dos objetos son iguales o sea que su método equals
     * retorna true el hashCode debe ser el mismo, porque el hashCode se usa para almacenar en baldes
     * y la funcion hashCode puede tener valores duplicados y el equals define si son realmente iguales
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MapKey other = (MapKey) obj;
        return Objects.equals(this.key, other.key);
    }

    @Override
    public String toString() {
        return "MapKey{" + "key=" + key + '}';
    }
}
