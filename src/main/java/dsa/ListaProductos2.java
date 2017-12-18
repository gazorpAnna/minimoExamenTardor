package dsa;

import java.util.List;

public class ListaProductos2 {
    private List<Producto> listaProductos;

    public ListaProductos2(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
