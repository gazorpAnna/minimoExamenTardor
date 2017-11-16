package dsa;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/json")
public class JSONService {
    protected ProductManagerImpl impl;
    List<Producto> productosJSON;
    Boolean done;
    public JSONService(){
        done = false;
        List<Producto> productosJSON = new ArrayList<>();
        impl = ProductManagerImpl.getInstance();


        Usuario qq = new Usuario("anna");
        impl.listaUsuarios.put(qq.getNombre(),qq);

        Usuario qq2 = new Usuario("bernat");
        impl.listaUsuarios.put(qq2.getNombre(),qq2);
        List<Producto> listProd= impl.listaOrdenadaProductos();

        List<Producto> listaProductosPedido = new ArrayList<>();
        Producto p = new Producto(listProd.get(0));
        Producto pp = new Producto(listProd.get(1));
        listaProductosPedido.add(p);
        listaProductosPedido.add(pp);
        listaProductosPedido.add(p);
        PedidoProducto pedido = new PedidoProducto(listaProductosPedido, qq);

        impl.realizarPedido(pedido);
        //impl.realizarPedido(pedido);
        //HashMap<Producto, Integer> pedido = new HashMap<>();
        //Producto p = impl.getMenuProductos().getProductoDeLista("bocata");
        //pedido.put(p,2);
    }

    @Path("/listaOrdenadaPrecio")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listaOrdenadaPrecio()
    {
        String res = "La lista ordenada por precio ascente: \n";

        List<Producto> lista = impl.listaOrdenadaProductos();
        for (Producto product: lista) {
            res+=product.toString() + "\n";
        }
        return res;
    }

    @Path("/servirPedido")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response servirPedido()
    {
        if(impl.servirPedido())
            return Response.status(201).entity("Pedido servido").build();
        else
            return Response.status(201).entity("No hay pedidos por servir").build();
    }

    @Path("/listadoPedidosUsuario/{usuario}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listadoPedidosUsuario(@PathParam("usuario") String usuario){
        if(impl.listadoPedidosUsuario(impl.buscarUsuario(usuario)).isEmpty())
            return "Este usuario no ha realizado pedidos";
        String res = "Listado de pedidos del usuario "+usuario+"\n";
        int i = 1;
        for (PedidoProducto ped:impl.listadoPedidosUsuario(impl.buscarUsuario(usuario))) {
            res += "Pedido " + i + "\n";
            for (Producto prod: ped.getListaProductos()) {
                res += prod.toString() + "\n";
            }
            i++;
        }


        return res;
    }

    @Path("/listadoProductosOrdenadoCant")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listadoProductosOrdenadosCantidad(){
        if(impl.listadoProductosCantidad().isEmpty())
            return "No se ha vendido ningun producto";
        String res = "Lista de productos ordenada por ventas: \n";
        for (ProductoCant pr:impl.listadoProductosCantidad()) {
            res += pr.toString() + "\n";
        }

        return res;
    }



    @Path("/newListaProductos")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevaListaProductos(List<Producto> lista) {
        productosJSON = lista;
        //HashMap<Producto, Integer> pedido = new HashMap<>();
        //Producto p = impl.getMenuProductos().getProductoDeLista(nombreProducto);
        //pedido.put(p,cant);

        done = true;
        //impl.realizarPedido(pedido);
        return Response.status(201).entity("Lista añadida").build();
    }

    @Path("/realizarPedido/{usuario}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarPediso(@PathParam("usuario") String usuario) {
        if (done) {
            PedidoProducto pre = new PedidoProducto(productosJSON, impl.buscarUsuario(usuario));
            impl.realizarPedido(pre);
            done = false;
            return Response.status(201).entity("Pedido de " + usuario + " realizado").build();
        } else
            return Response.status(201).entity("Llena la lista de productos antes: /newListaProductos").build();
    }




}
