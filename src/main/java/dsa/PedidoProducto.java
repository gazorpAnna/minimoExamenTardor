package dsa;

import java.util.ArrayList;
import java.util.List;

public class PedidoProducto {

    //Producto producto;
    List<Producto> listaProductos = new ArrayList<>();
    Usuario usu;

    public PedidoProducto(List<Producto> listaProductos, Usuario usu) {
        this.listaProductos = listaProductos;
        this.usu = usu;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public Usuario getUsu() {
        return usu;
    }


    /*
    public PedidoProducto(Producto producto, Usuario usu) {
        this.producto = producto;
        this.usu = usu;
    }

    public Producto getProducto() {
        return producto;
    }

    public Usuario getUsu() {
        return usu;
    }*/
}
