/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Colas;


import java.util.LinkedList;
import java.util.Queue;

public class ColaArticulos {
    private Queue<Articulo> cola;

    public ColaArticulos() {
        cola = new LinkedList<>();
    }

    public void agregarArticulo(Articulo articulo) {
        cola.offer(articulo);
    }

    public Articulo eliminarArticulo() {
        if (!cola.isEmpty()) {
            return cola.poll();
        }
        return null;
    }

    public Queue<Articulo> getCola() {
        return cola;
    }

    public double calcularTotalRecursivo(Queue<Articulo> cola) {
        Queue<Articulo> colaTemporal = new LinkedList<>(cola); // Crear una copia de la cola
        if (colaTemporal.isEmpty()) {
            return 0;
        } else {
            Articulo articulo = colaTemporal.poll();
            double total = articulo.getResultado() + calcularTotalRecursivo(colaTemporal);
            return total;
        }
    }
}