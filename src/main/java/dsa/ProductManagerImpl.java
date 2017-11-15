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



    public ProductManagerImpl() {
        //menuProductos = new ListaProductos();
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
    /*public ListaProductos getMenuProductos() {
        return menuProductos;
    }*/

    public List<Producto> getMenuProductos() {
        return menuProductos;
    }

    public Usuario buscarUsuario(String nombre)
    {
        return listaUsuarios.get(nombre);
    }


    // MÉTODES DE LA INTERFÍCIE
    @Override
    public List<Producto> listaOrdenadaProductos() {
        if (!menuProductos.isEmpty()) {
            List<Producto> ant = new ArrayList<>();
            List<Producto> result = new ArrayList<>();
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
            return result;
        } else
            return null;
    }

    @Override
    public boolean realizarPedido(PedidoProducto pedido) {//HashMap<Producto, Integer> p, Usuario u) {
        // Afegir un pedido a la pedidosRealizados
        // Afegir pedido a la colaPedido

        pedidiosRealizados.add(pedido);
        colaPedidos.add(pedido);


        /*log.info("Añado un pedido al usuario "+ u.getNombre());
        for (Map.Entry<Producto, Integer> a : p.entrySet()){
            for (int cont = 0; cont < a.getValue(); cont++)
            {

                u.getPedidosRealizados().add(a.getKey());
                PedidoProducto pedprod = new PedidoProducto(a.getKey(),u);
                log.info("Añado el producot "+ pedprod.getProducto().getNombreProd() + "A la lista de pedidosRealizados");
                menuProductos.getPedidosRealizados().add(pedprod);
                ProductoCant prod = menuProductos.getProdCant(a.getKey().getNombreProd());
                prod.setCant(prod.getCant()+1);
                log.info("Incremento la cantidad en la lista de Productos general");
            }

        }
        listaUsuarios.replace(u.getNombre(),u);
        log.info("Reemplazo el usuario modificado en la lista de usuarios");*/
        return true;
    }

    @Override
    public boolean servirPedido() {
        pedidiosServidos.add(colaPedidos.poll());

/*
        PedidoProducto ped = menuProductos.getPedidosRealizados().poll();
        log.info("Saco el pedido de la cola "+ped.getProducto().getNombreProd()+ "que va destinado al usuario " + ped.getUsu().getNombre());
        Usuario usu = listaUsuarios.get(ped.getUsu().getNombre());
        usu.transferPedido(ped.getProducto());
        log.info("Añado el pedido en la lista de pedidos servidos del Usuario sin quitarlo de la lista de pedidos realizados");
*/
        return true;
    }

    @Override
    public List<PedidoProducto> listadoPedidosUsuario(Usuario u) {
        log.info("retorno el listado de pedidos total que tiene un usuario");
        List<PedidoProducto> result = new ArrayList<>();
        for (PedidoProducto p: pedidiosRealizados) {
            if (p.getUsu().equals(u))
                result.add(p);
        }
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
        log.info("intento de ordenar los productos por numero de ventas");
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

                    //int i = result.indexOf(p);
                    //result.get(i).setCant(result.get(i).getCant()+1);
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
        } else
            return null;

        ///// NO VA, JA QUE EM TREU ELS OBJECTES DE LA LLISTA PRINCIPAL??????????????

        //Comparator<Integer> comparador = Collections.reverseOrder();
        //Collections.sort(menuProductos, comparador);
        /*Collections.sort(menuProductos, new Comparator () {
            @Override
            public int compare(ProductoCant o1, ProductoCant o2) {
                return new Integer(o2.getCant()).compareTo(new Integer(o1.getCant()));
            }
        });*/
        /*List<ProductoCant> result = new ArrayList<>();
        List<ProductoCant> ant = menuProductos.getListaProductos();
        List<ProductoCant> act = ant;

        ProductoCant prod = menuProductos.getListaProductos().get(0);
        int max = prod.getCant();

        for (ProductoCant i: act) {
            for (ProductoCant ii:ant) {
                if(max < ii.getCant())
                {
                    max = ii.getCant();
                    prod = ii;
                }
            }
            result.add(prod);
            ant.remove(prod);
            prod = null;
            max=-1;
        }
        return result;*/
    }
}
