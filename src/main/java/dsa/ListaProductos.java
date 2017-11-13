package dsa;

import java.util.*;

public class ListaProductos {
    //HashMap<Producto,Integer> listaProductos = new HashMap<>();

    List<ProductoCant> listaProductos = new ArrayList<>();
    Queue<PedidoProducto> pedidosRealizados = new LinkedList<>();


    public Queue<PedidoProducto> getPedidosRealizados() {
        return pedidosRealizados;
    }

    public ListaProductos() {
        Producto p = new Producto("bocata", 3);
        ProductoCant pc = new ProductoCant(p,0);
        this.listaProductos.add(pc);
        Producto pp = new Producto("ensalada",6);
        ProductoCant ppc = new ProductoCant(pp,0);
        this.listaProductos.add(ppc);
    }


    public ProductoCant getProdCant (String nombre)
    {
        for (ProductoCant d:listaProductos) {
            if (d.getProd().getNombreProd().contains(nombre))
                return d;
        }
        return null;
    }

    public List<ProductoCant> getListaProductos() {
        return listaProductos;
    }

    public Producto getProductoDeLista (String nombre)
    {
        for (ProductoCant pc: listaProductos) {
            if (pc.getProd().getNombreProd().contains(nombre));
            {
                return pc.getProd();
            }
        }
        return null;
    }
}
