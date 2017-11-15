package dsa;

import javax.ws.rs.*;
import java.util.HashMap;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@Path("/json")
public class JSONService {
    protected ProductManagerImpl impl;

    public JSONService(){
        impl = ProductManagerImpl.getInstance();

        Usuario qq = new Usuario("anna");
        impl.listaUsuarios.put(qq.getNombre(),qq);
        HashMap<Producto, Integer> pedido = new HashMap<>();
        //Producto p = impl.getMenuProductos().getProductoDeLista("bocata");
        //pedido.put(p,2);
    }

    @Path("/newPedido/{usuario}/{nombreProducto}/{cant}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoPedido(@PathParam("usuario")String usuario, @PathParam("nombreProducto")String nombreProducto, @PathParam("cant") Integer cant) {
        HashMap<Producto, Integer> pedido = new HashMap<>();
        //Producto p = impl.getMenuProductos().getProductoDeLista(nombreProducto);
        //pedido.put(p,cant);

        //impl.realizarPedido(pedido);
        return null;
    }
}
