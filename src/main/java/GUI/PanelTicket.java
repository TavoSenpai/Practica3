package GUI;

import javax.swing.*;
import java.awt.*;
import Colas.Articulo;
import Colas.ColaArticulos;

public class PanelTicket extends JPanel {

    public JTextArea txtTicket;

    public ColaArticulos colaArticulos; // Cambiado a ColaArticulos

    public PanelTicket(ColaArticulos colaArticulos) {
        this.colaArticulos = colaArticulos; // Cambiado a colaArticulos
        setLayout(new BorderLayout());

        txtTicket = new JTextArea();
        txtTicket.setEditable(false);
        txtTicket.setFont(new Font("SansSerif", Font.PLAIN, 12));
        add(new JScrollPane(txtTicket), BorderLayout.CENTER);
    }

    public void actualizarTicket() {
        txtTicket.setText("");
        txtTicket.append("  Tienda\n");
        txtTicket.append("  UAPT 16-03\n");
        txtTicket.append("  Telef. 11223344\n");
        txtTicket.append("---------------------------------------------------------\n");
        txtTicket.append("  Recibo de Pago\n");
        txtTicket.append("---------------------------------------------------------\n");
        txtTicket.append("Producto  Precio  Desc.\n");
        txtTicket.append("---------------------------------------------------------\n");

        double total = 0;
        for (Articulo articulo : colaArticulos.getCola()) {
            double precioOriginal = articulo.getCantidad() * articulo.getPrecio();
            txtTicket.append(String.format("%-8s %6.2f %6.2f\n", articulo.getNombre(), precioOriginal, articulo.getResultado()));
            if (articulo.getDescuentoAplicado()) {
                txtTicket.append("  Descuento Aplicado\n");
            }
            total += articulo.getResultado();
        }

        txtTicket.append("---------------------------------------------------------\n");
        txtTicket.append(String.format("Total     %6.2f\n", total));
        txtTicket.append("---------------------------------------------------------\n");
        txtTicket.append("  GRACIAS POR SU COMPRA!\n");
        txtTicket.append("---------------------------------------------------------\n");
    }

    public void setColaArticulos(ColaArticulos colaArticulos) {
        this.colaArticulos = colaArticulos;
    }

    public void limpiarTicket() {
        txtTicket.setText("");
    }
}
