package Control;

import Dataset.MyDataset;
import Modelo.Sheet;
import Vista.IVista;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ControlSheets implements Control {
    private IVista vista;
    private Sheet modelo;

    public ControlSheets(IVista vista, Sheet modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public MyDataset getData(String dir, String rang) throws GeneralSecurityException, IOException {
        modelo.setData(dir, rang);
        MyDataset dataset = new MyDataset(modelo.getData());
        return dataset;
    }
}
