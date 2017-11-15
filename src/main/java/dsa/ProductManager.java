package dsa;

import java.util.HashMap;
import java.util.List;

public interface ProductManager {


    List<Producto> listaOrdenadaProductos();

    boolean realizarPedido(PedidoProducto p);

    boolean servirPedido();

    List<PedidoProducto> listadoPedidosUsuario(Usuario u);

    List<ProductoCant> listadoProductosCantidad();






}
