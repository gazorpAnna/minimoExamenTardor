package dsa;

import java.util.*;

import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager{

    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());
    private static ProductManagerImpl instance;

    HashMap<String,Usuario>     listaUsuarios;
    //ListaProductos              menuProductos;
    List<Producto>              menuProductos;
    List<PedidoProducto>        pedidiosRealizados;
    List<PedidoProducto>        pedidiosServidos;
    Queue<PedidoProducto>       colaPedidos;

    public List<PedidoProducto> getPedidiosRealizados() {
        return pedidiosRealizados;
    }

    public List<PedidoProducto> getPedidiosServidos() {
        return pedidiosServidos;
    }

    public Queue<PedidoProducto> getColaPedidos() {
        return colaPedidos;
    }

    public ProductManagerImpl() {
        menuProductos = new ArrayList<>();
        Producto p = new Producto("bocata",3);
        Producto pp = new Producto("ensalada",5);
        Producto ppp  = new Producto("pan",1);
        menuProductos.add(p);menuProductos.add(pp);menuProductos.add(ppp);

        pedidiosRealizados = new ArrayList<>();
        pedidiosServidos = new ArrayList<>();
        colaPedidos = new LinkedList<>();
        listaUsuarios = new HashMap<>();
        log.info("listas inicializadas");
    }
    public static ProductManagerImpl getInstance() {
        if(instance == null)
        {
            instance = new ProductManagerImpl();
            log.info("Se crea la instancia ProductManagerImpl por primera vez");
        }
        return instance;
    }
    public void reset()
    {
        this.instance = null;
    }


    public HashMap<String, Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    public List<Producto> getMenuProductos() {
        return menuProductos;
    }

    public Usuario buscarUsuario(String nombre)
    {
        if (!listaUsuarios.containsKey(nombre))
            log.error("El usuario "+nombre+" no ha sido encontrado.");
        return listaUsuarios.get(nombre);
    }


    // MÉTODES DE LA INTERFÍCIE
    @Override
    public List<Producto> listaOrdenadaProductos() {
        if (!menuProductos.isEmpty()) {
            List<Producto> ant = new ArrayList<>();
            List<Producto> result = new ArrayList<>();
            List<Producto> resultReverse = new ArrayList<>();
            for (Producto p : menuProductos) {
                ant.add(p);
            }
            int         max;
            Producto    prod;
            for (Producto p : menuProductos) {
                max = ant.get(0).getPrecio();
                prod = ant.get(0);
                for (Producto pp:ant) {
                    if(max<pp.getPrecio()) {
                        max = pp.getPrecio();
                        prod = pp;
                    }
                }
                result.add(prod);
                ant.remove(prod);
            }
            Collections.reverse(result);
            return result;
        } else {
            log.error("No hay productos en el menu.");
            return null;
        }
    }

    @Override
    public boolean realizarPedido(PedidoProducto pedido) {//HashMap<Producto, Integer> p, Usuario u) {
        // Afegir un pedido a la pedidosRealizados
        // Afegir pedido a la colaPedido
        log.info("Se añade el pedido del usuario "+pedido.getUsu().getNombre()+ " a pedidosRealizados y a la cola de pedidos.");
        pedidiosRealizados.add(pedido);
        colaPedidos.add(pedido);
        return true;
    }

    @Override
    public boolean servirPedido() {
        if (colaPedidos.poll() == null) {
            log.info("No hay pedidos para servir.");
            return false;
        } else
        {
            log.info("Se sirve el pedido "); //+ colaPedidos.poll().getUsu().getNombre() + " y se añade el pedido a pedidos servidos.");
            pedidiosServidos.add(colaPedidos.poll());
            return true;
        }
    }

    @Override
    public List<PedidoProducto> listadoPedidosUsuario(Usuario u) {
        log.info("retorno el listado de pedidos total que tiene un usuario");
        List<PedidoProducto> result = new ArrayList<>();
        for (PedidoProducto p: pedidiosRealizados) {
            if (p.getUsu().equals(u))
                result.add(p);
        }
        if (result.isEmpty())
            log.info("El usuario "+u.getNombre() + " no ha realizado ningun pedido.");
        return result;
        //return u.getPedidosRealizados();
    }

    public boolean estaProducto (List<ProductoCant> lista, String nombreProducto)
    {
        for (ProductoCant p: lista) {
            if (p.getProd().getNombreProd().contains(nombreProducto))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<ProductoCant> listadoProductosCantidad() {
        // Supongo que son los pedidos servidos
        log.info("lista para ordenar los productos por número de ventas");
        List<ProductoCant> result = new ArrayList<>();
        for (PedidoProducto pedido: pedidiosServidos) {
            for (Producto p:pedido.getListaProductos()) {
                if(estaProducto(result,p.getNombreProd()))
                {
                    // si esta el producto en result, incremento la cantidad
                    for (ProductoCant pr:result) {
                        if(pr.getProd().getNombreProd().contains(p.getNombreProd())) {
                            pr.setCant(pr.getCant() + 1);
                            break;
                        }
                    }
                } else
                {
                    // si no esta, añado un registro de ese producto
                    result.add(new ProductoCant(p,1));
                }
            }
        }
        // aixo se suposa que em treu una llista amb productes unics i la quantitat venuda
        // ara a ordenar-ho

        if (!result.isEmpty()) {
            List<ProductoCant> antFin = new ArrayList<>();
            List<ProductoCant> resultFin = new ArrayList<>();
            for (ProductoCant p : result) {
                antFin.add(p);
            }
            int         max;
            ProductoCant    prod;
            for (ProductoCant p : result) {
                max = antFin.get(0).getCant();
                prod = antFin.get(0);
                for (ProductoCant pp:antFin) {
                    if(max<pp.getCant()) {
                        max = pp.getCant();
                        prod = pp;
                    }
                }
                resultFin.add(prod);
                antFin.remove(prod);
            }
            return resultFin;
        } else {
            log.info("No se ha vendido ningún producto aún.");
            return null;
        }


        /// No va el comparador, per això he fet el que està a dalt...
        //Comparator<Integer> comparador = Collections.reverseOrder();
        //Collections.sort(menuProductos, comparador);
        /*Collections.sort(menuProductos, new Comparator () {
            @Override
            public int compare(ProductoCant o1, ProductoCant o2) {
                return new Integer(o2.getCant()).compareTo(new Integer(o1.getCant()));
            }
        });*/
    }
}
