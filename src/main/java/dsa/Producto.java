package dsa;

public class Producto {
    String nombreProd;
    int precio;

    public Producto(String nombreProd, int precio) {
        this.nombreProd = nombreProd;
        this.precio = precio;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public int getPrecio() {
        return precio;
    }
}
