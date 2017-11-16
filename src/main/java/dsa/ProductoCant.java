package dsa;

public class ProductoCant {
    Producto prod;
    Integer cant;

    public ProductoCant(Producto prod, Integer cant) {
        this.prod = prod;
        this.cant = cant;
    }

    public Producto getProd() {
        return prod;
    }

    public Integer getCant() {
        return cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }

    @Override
    public String toString() {
        return "ProductoCant{" +
                "prod=" + prod +
                ", cant=" + cant +
                '}';
    }
}
