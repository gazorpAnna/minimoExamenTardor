package dsa;

public class Producto {
    String nombreProd;
    int precio;

    public Producto(String nombreProd, int precio) {
        this.nombreProd = nombreProd;
        this.precio = precio;
    }

    public Producto(Producto p){
        this.nombreProd = p.getNombreProd();
        this.precio = p.getPrecio();
    }


    public String getNombreProd() {
        return nombreProd;
    }

    public int getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombreProd='" + nombreProd + '\'' +
                ", precio=" + precio +
                '}';
    }
}
