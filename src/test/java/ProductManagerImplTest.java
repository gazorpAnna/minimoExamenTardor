import dsa.PedidoProducto;
import dsa.ProductManagerImpl;
import dsa.Producto;
import dsa.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProductManagerImplTest {
    ProductManagerImpl impl;

    Usuario qq, qqq;
    PedidoProducto pedido, pedido2;
    Producto p, p2;
    List<Producto> listaProductos;

    @Before
    public void SetUp ()
    {
        impl = ProductManagerImpl.getInstance();
        qq = new Usuario("anna");
        impl.getListaUsuarios().put(qq.getNombre(),qq);
        //pedido = new HashMap<>();
        //p = impl.getMenuProductos().get(0);
        listaProductos = new ArrayList<>();
        listaProductos.add(impl.getMenuProductos().get(0));
        listaProductos.add(impl.getMenuProductos().get(1));
        pedido = new PedidoProducto(listaProductos,qq);


        qqq = new Usuario("bernat");
        impl.getListaUsuarios().put(qqq.getNombre(),qqq);
        //pedido2 = new HashMap<>();
        listaProductos.add(impl.getMenuProductos().get(2));
        listaProductos.add(impl.getMenuProductos().get(1));
        pedido2 = new PedidoProducto(listaProductos,qq);

        //p2 = impl.getMenuProductos().getProductoDeLista("ensalada");
        impl.realizarPedido(pedido2);
    }

    @After
    public void tearDown()
    {
        // Cierro las connexiones de SQL
        impl.reset();
    }

    @Test
    public void realizarPedidoTest()
    {
        assertTrue(impl.realizarPedido(pedido));
        assertEquals(2, impl.getPedidiosRealizados().size());
        assertEquals(2, impl.getColaPedidos().size());
        //assertEquals(2, impl.getMenuProductos().getPedidosRealizados().size());
    }

    @Test
    public void servirPedidoTest()
    {
        assertTrue(impl.servirPedido());
        assertEquals(1, impl.getPedidiosRealizados().size());
        assertEquals(0, impl.getColaPedidos().size());
    }
}
