package dsa;

import java.util.HashMap;
import java.util.List;

public interface ProductManager {


    List<Producto> listaOrdenadaProductos();

    boolean realizarPedido(HashMap<Producto,Integer> p, Usuario u);

    boolean servirPedido();

    List<Producto> listadoProductosUsuario(Usuario u);

    List<ProductoCant> listadoProductosCantidad();






}
