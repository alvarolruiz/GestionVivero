package Entidades.Documentos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carrito {
    private Map<Integer, Integer> articulos;

    public Carrito() {
        this.articulos = new HashMap<>();
    }

    // Devolverá true si se ha podido añadir con exito (debe haber stock y debe existir)
    public boolean addArticulo(int idProducto, int cantidad){
        articulos.put(idProducto, cantidad);
        return true;

    }



}
