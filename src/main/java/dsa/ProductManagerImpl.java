package dsa;

import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager{

    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());

    private static ProductManagerImpl instance;


    public ProductManagerImpl() {

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
        return false;
    }

    @Override
    public boolean servirPedido() {
        return false;
    }

    @Override
    public List<Producto> listadoProductosUsuario(Usuario u) {
        return null;
    }

    @Override
    public List<Producto> listadoProductosCantidad() {
        return null;
    }
}
