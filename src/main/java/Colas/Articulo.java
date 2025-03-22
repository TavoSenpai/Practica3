package Colas;

public class Articulo {
    private String nombre;
    private int cantidad;
    private double precio;
    private double resultado;
    private boolean descuentoAplicado;

    public Articulo(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.resultado = cantidad * precio;
        this.descuentoAplicado = false;
        if (cantidad > 5) {
            this.resultado = this.resultado * 0.8; // Aplicar descuento del 20%
            this.descuentoAplicado = true;
        }
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public double getResultado() {
        return resultado;
    }

    public boolean getDescuentoAplicado() {
        return descuentoAplicado;
    }
}