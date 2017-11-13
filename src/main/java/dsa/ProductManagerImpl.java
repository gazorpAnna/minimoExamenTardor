package dsa;

import java.util.*;

import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager{

    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());

    HashMap<String,Usuario> listaUsuarios;
    ListaProductos listaProductos;

    private static ProductManagerImpl instance;

    public HashMap<String, Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public ListaProductos getListaProductos() {
        return listaProductos;
    }

    public ProductManagerImpl() {
        listaProductos = new ListaProductos();
        listaUsuarios = new HashMap<>();
        log.info("listas de productos y usuarios inicializadas");
    }

    public static ProductManagerImpl getInstance()
    {
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



    @Override
    public List<Producto> listaOrdenadaProductos() {
        return null;
    }

    @Override
    public boolean realizarPedido(HashMap<Producto, Integer> p, Usuario u) {
        log.info("Añado un pedido al usuario "+ u.getNombre());
        for (Map.Entry<Producto, Integer> a : p.entrySet()){
            for (int cont = 0; cont < a.getValue(); cont++)
            {

                u.getPedidosRealizados().add(a.getKey());
                PedidoProducto pedprod = new PedidoProducto(a.getKey(),u);
                log.info("Añado el producot "+ pedprod.getProducto().getNombreProd() + "A la lista de pedidosRealizados");
                listaProductos.getPedidosRealizados().add(pedprod);
                ProductoCant prod = listaProductos.getProdCant(a.getKey().getNombreProd());
                prod.setCant(prod.getCant()+1);
                log.info("Incremento la cantidad en la lista de Productos general");
            }

        }
        listaUsuarios.replace(u.getNombre(),u);
        log.info("Reemplazo el usuario modificado en la lista de usuarios");
        return true;
    }

    @Override
    public boolean servirPedido() {

        PedidoProducto ped = listaProductos.getPedidosRealizados().poll();
        log.info("Saco el pedido de la cola "+ped.getProducto().getNombreProd()+ "que va destinado al usuario " + ped.getUsu().getNombre());
        Usuario usu = listaUsuarios.get(ped.getUsu().getNombre());
        usu.transferPedido(ped.getProducto());
        log.info("Añado el pedido en la lista de pedidos servidos del Usuario sin quitarlo de la lista de pedidos realizados");

        return true;
    }

    @Override
    public List<Producto> listadoProductosUsuario(Usuario u) {
        log.info("retorno el listado de pedidos total que tiene un usuario");
        return u.getPedidosRealizados();
    }

    @Override
    public List<ProductoCant> listadoProductosCantidad() {
        log.info("intento de ordenar los productos por numero de ventas");
        ///// NO VA, JA QUE EM TREU ELS OBJECTES DE LA LLISTA PRINCIPAL??????????????

        //Comparator<Integer> comparador = Collections.reverseOrder();
        //Collections.sort(listaProductos, comparador);
        /*Collections.sort(listaProductos, new Comparator () {
            @Override
            public int compare(ProductoCant o1, ProductoCant o2) {
                return new Integer(o2.getCant()).compareTo(new Integer(o1.getCant()));
            }
        });*/
        List<ProductoCant> result = new ArrayList<>();
        List<ProductoCant> ant = listaProductos.getListaProductos();
        List<ProductoCant> act = listaProductos.getListaProductos();

        ProductoCant prod = listaProductos.getListaProductos().get(0);
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
        return result;
    }
}
