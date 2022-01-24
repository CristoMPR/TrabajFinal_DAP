package Vista;

import Control.*;
import Dataset.MyDataset;

public class Grafica implements IVista {
    private ControlSheets controlador;
    private MyDataset dataset;
    @Override
    public void setControlador(ControlSheets controlador) {
        this.controlador = controlador;
    }

    @Override
    public void start() {
        Ventana miVentana = new Ventana(controlador);
    }
}
