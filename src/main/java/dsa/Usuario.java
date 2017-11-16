package dsa;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    String nombre;

    //List<Producto> pedidosRealizados = new ArrayList<>();
    //List<Producto> pedidosServidos = new ArrayList<>();

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    /*
    public List<Producto> getPedidosRealizados() {
        return pedidosRealizados;
    }

    public List<Producto> getPedidosServidos() {
        return pedidosServidos;
    }

    public void transferPedido (Producto p)
    {
        //pedidosRealizados.remove(p);
        pedidosServidos.add(p);
    }*/
}
