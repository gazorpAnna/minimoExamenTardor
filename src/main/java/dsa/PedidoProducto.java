package dsa;

public class PedidoProducto {
    Producto producto;
    Usuario usu;

    public PedidoProducto(Producto producto, Usuario usu) {
        this.producto = producto;
        this.usu = usu;
    }

    public Producto getProducto() {
        return producto;
    }

    public Usuario getUsu() {
        return usu;
    }
}
