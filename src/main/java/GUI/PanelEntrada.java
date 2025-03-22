
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Colas.Articulo;
import Colas.ColaArticulos;




public class PanelEntrada extends JPanel {
    private JTextField txtNombre, txtCantidad, txtPrecio;
    private JButton btnAgregar, btnEliminar, btnPagar, btnVistaPrevia;
    private ColaArticulos colaArticulos; // Cambiado a ColaArticulos
    private VentanaPrincipal ventanaPrincipal;

    public PanelEntrada(ColaArticulos colaArticulos, VentanaPrincipal ventanaPrincipal) {
        this.colaArticulos = colaArticulos; // Cambiado a colaArticulos
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout()); // Usamos BorderLayout para centrar los botones

        JPanel inputPanel = new JPanel(new GridLayout(3, 2)); // Panel para los campos de entrada
        add(inputPanel, BorderLayout.NORTH); // Colocamos el panel de entrada en la parte superior

        inputPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        inputPanel.add(txtNombre);

        inputPanel.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        inputPanel.add(txtCantidad);

        inputPanel.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        inputPanel.add(txtPrecio);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel para los botones
        add(buttonPanel, BorderLayout.SOUTH); // Colocamos el panel de botones en la parte inferior

        btnAgregar = new JButton("Agregar");
        btnAgregar.setPreferredSize(new Dimension(80, 25)); 
        btnAgregar.addActionListener(e -> agregarArticulo());
        buttonPanel.add(btnAgregar);

    btnEliminar = new JButton("Eliminar");
    btnEliminar.setPreferredSize(new Dimension(80, 25));
    btnEliminar.addActionListener(e -> {
        eliminarArticuloSeleccionado();
    });
    buttonPanel.add(btnEliminar);

    btnVistaPrevia = new JButton("Vista Previa");
    btnVistaPrevia.setPreferredSize(new Dimension(100, 25));
    btnVistaPrevia.addActionListener(e -> {
       
        ventanaPrincipal.mostrarVistaPreviaTicket();
    });
    buttonPanel.add(btnVistaPrevia);

        btnPagar = new JButton("Pagar");
        btnPagar.setPreferredSize(new Dimension(80, 25)); 
        btnPagar.addActionListener(e -> ventanaPrincipal.mostrarTicketFinal());
        buttonPanel.add(btnPagar);
    }


     private void agregarArticulo() {
        try {
            String nombre = txtNombre.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double precio = Double.parseDouble(txtPrecio.getText());

            // Verificar si el producto ya existe en la pila
            if (productoExiste(nombre)) {
                JOptionPane.showMessageDialog(this, "El producto ya está en el carrito.");
                return;
            }

            Articulo articulo = new Articulo(nombre, cantidad, precio);
            colaArticulos.agregarArticulo(articulo);

            txtNombre.setText("");
            txtCantidad.setText("");
            txtPrecio.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

   private boolean productoExiste(String nombre) {
        for (Articulo articulo : colaArticulos.getCola()) { // Cambiado a getCola()
            if (articulo.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

     private void eliminarArticuloSeleccionado() {
        // ... (lógica de eliminación adaptada a colas)
        if (colaArticulos.getCola().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay artículos para eliminar.");
            return;
        }

        String[] opciones = new String[colaArticulos.getCola().size()];
        int i = 0;
        for (Articulo articulo : colaArticulos.getCola()) {
            opciones[i++] = articulo.getNombre();
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el artículo a eliminar:",
                "Eliminar artículo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (seleccion != null) {
            ColaArticulos nuevaCola = new ColaArticulos();
            for (Articulo articulo : colaArticulos.getCola()) {
                if (!articulo.getNombre().equals(seleccion)) {
                    nuevaCola.agregarArticulo(articulo);
                }
            }
            colaArticulos = nuevaCola;
            ventanaPrincipal.setColaArticulos(colaArticulos);
        }
    }

    public void setColaArticulos(ColaArticulos colaArticulos) {
        this.colaArticulos = colaArticulos;
    }



    public void habilitarEntrada() {
        txtNombre.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtPrecio.setEnabled(true);
        btnAgregar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
}