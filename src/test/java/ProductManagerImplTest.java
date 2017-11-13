import dsa.ProductManagerImpl;
import dsa.Producto;
import dsa.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.validation.constraints.AssertTrue;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class ProductManagerImplTest {
    ProductManagerImpl impl;

    Usuario qq, qqq;
    HashMap<Producto, Integer> pedido, pedido2;
    Producto p, p2;

    @Before
    public void SetUp ()
    {
        impl = ProductManagerImpl.getInstance();
        qq = new Usuario("anna");
        impl.getListaUsuarios().put(qq.getNombre(),qq);
        pedido = new HashMap<>();
        p = impl.getListaProductos().getProductoDeLista("bocata");
        pedido.put(p,2);

        qqq = new Usuario("bernat");
        impl.getListaUsuarios().put(qqq.getNombre(),qqq);
        pedido2 = new HashMap<>();
        p2 = impl.getListaProductos().getProductoDeLista("ensalada");
        pedido2.put(p2,2);
        impl.realizarPedido(pedido2,qqq);
    }

    @After
    public void tearDown()
    {
        impl.reset();
    }

    @Test
    public void realizarPedidoTest()
    {
        assertTrue(impl.realizarPedido(pedido,qq));
        assertEquals(2, impl.getListaUsuarios().get(qq.getNombre()).getPedidosRealizados().size());

        assertEquals(2, impl.getListaProductos().getPedidosRealizados().size());
    }

    @Test
    public void servirPedidoTest()
    {
        assertTrue(impl.servirPedido());
        assertEquals(1, impl.getListaProductos().getPedidosRealizados().size());
        assertEquals(1, impl.getListaUsuarios().get(qqq.getNombre()).getPedidosServidos().size());
    }
}
