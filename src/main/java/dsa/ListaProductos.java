package dsa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ListaProductos {
    HashMap<Producto,Integer> listaProductos = new HashMap<>();

    Queue<Producto> pedidosRealizados = new LinkedList<>();

    public ListaProductos() {
        Producto p = new Producto("bocata", 3);
        this.listaProductos.put(p,0);
        Producto pp = new Producto("ensalada",6);
        this.listaProductos.put(pp,0);
    }

    public HashMap<Producto, Integer> getListaProductos() {
        return listaProductos;
    }
}
