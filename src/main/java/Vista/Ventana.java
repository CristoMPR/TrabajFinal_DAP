/**
 * Clase Frame:
 * Contiene el principal frame de la aplicación
 */

package Vista;

import Control.ControlSheets;

import javax.swing.*;

public class Ventana extends JFrame {
    private Panel panel;

    public Ventana(ControlSheets controlador) {
        panel = new Panel(controlador);
        setTitle("Panel de Control");
        add(panel);
        setSize(1300, 700);
        setResizable(false);
        setDefaultCloseOperation(2);
        setVisible(true);
    }

}