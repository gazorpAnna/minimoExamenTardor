package dsa;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
/*
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8088/examen/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    //public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
/*
        final ResourceConfig rc = new ResourceConfig().packages("dsa");


        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);*/
    //}

    /**
     * packJson.Main method.
     * @param args
     * @throws IOException
     */

    public static void main(String args[]) throws IOException {
/*
        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");


        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.in.read();
        server.stop();*/
        ProductManagerImpl impl = ProductManagerImpl.getInstance();

        Usuario qq = new Usuario("anna");
        impl.listaUsuarios.put("anna",qq);

        Usuario qq2 = new Usuario("bernat");
        impl.listaUsuarios.put(qq2.getNombre(),qq2);


        List<Producto> listProd = impl.listaOrdenadaProductos();

        List<Producto> listaProductosPedido = new ArrayList<>();
        Producto p = new Producto(listProd.get(0));
        Producto pp = new Producto(listProd.get(1));
        listaProductosPedido.add(p);
        listaProductosPedido.add(pp);
        listaProductosPedido.add(p);
        PedidoProducto pedido = new PedidoProducto(listaProductosPedido, qq);
        impl.realizarPedido(pedido);

        impl.servirPedido();

        listaProductosPedido.add(new Producto(listProd.get(2)));
        PedidoProducto pedido2 = new PedidoProducto(listaProductosPedido, qq2);
        impl.realizarPedido(pedido2);

        impl.servirPedido();

        List<ProductoCant> prodCant = impl.listadoProductosCantidad();
        List<PedidoProducto> pedProdUsu = impl.listadoPedidosUsuario(qq2);
        int lala = 1;
        //HashMap<Producto, Integer> pedido = new HashMap<>();
        //Producto p = impl.menuProductos.getProductoDeLista("bocata");
        //pedido.put(p,2);
        //impl.realizarPedido(pedido,qq);
        //impl.servirPedido();

        //List<ProductoCant> result= impl.listadoProductosCantidad();

    }
}
