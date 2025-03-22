package GUI;

import javax.swing.*;
import java.awt.*;
import Colas.ColaArticulos;

public class VentanaPrincipal extends JFrame {
    private PanelEntrada panelEntrada;
    private PanelTicket panelTicket;
    private ColaArticulos colaArticulos;

    public VentanaPrincipal() {
        setTitle("Caja Registradora");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        colaArticulos = new ColaArticulos();
        panelEntrada = new PanelEntrada(colaArticulos, this);
        panelTicket = new PanelTicket(colaArticulos);

        add(panelEntrada, BorderLayout.WEST);
        setVisible(true);
    }

    

    public void mostrarVistaPreviaTicket() {
        JDialog ticketDialog = new JDialog(this, "Vista Previa del Ticket", true);
        ticketDialog.setLayout(new BorderLayout());
        PanelTicket ticketPanel = new PanelTicket(colaArticulos);
        ticketPanel.actualizarTicket();
        ticketDialog.add(new JScrollPane(ticketPanel.txtTicket), BorderLayout.CENTER);
        ticketDialog.setSize(250, 350); // Ajustamos el tamaño de la ventana
        ticketDialog.setLocationRelativeTo(this);
        ticketDialog.setVisible(true);
    }

    public void mostrarTicketFinal() {
        if (colaArticulos.getCola().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos en el carrito.");
            return;
        }

        JDialog ticketDialog = new JDialog(this, "Ticket de Compra", true);
        ticketDialog.setLayout(new BorderLayout());
        panelTicket.actualizarTicket();
        ticketDialog.add(new JScrollPane(panelTicket.txtTicket), BorderLayout.CENTER);
        ticketDialog.setSize(250, 350); 
        ticketDialog.setLocationRelativeTo(this);
        ticketDialog.setVisible(true);

        panelTicket.limpiarTicket();
        colaArticulos = new ColaArticulos();
        panelTicket.setColaArticulos(colaArticulos);
        panelEntrada.setColaArticulos(colaArticulos);
        
        panelEntrada.habilitarEntrada();
    }

    public void setColaArticulos(ColaArticulos colaArticulos) {
        this.colaArticulos = colaArticulos;
        panelTicket.setColaArticulos(colaArticulos);
        panelEntrada.setColaArticulos(colaArticulos);
    }
}