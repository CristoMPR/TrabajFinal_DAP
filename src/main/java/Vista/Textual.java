package Vista;

import Control.ControlSheets;
import Dataset.MyDataset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

public class Textual implements IVista {
    private ControlSheets controlador;
    private MyDataset dataset;
    // Gestión de la entrada por teclado
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void setControlador(ControlSheets controlador) {
        this.controlador = controlador;
    }

    @Override
    public void start() throws GeneralSecurityException, IOException {
        selectSheet();
    }

    public void selectSheet() throws GeneralSecurityException, IOException {
        System.out.print("Indica la dirección del documento: ");
        String dir = getText();
        System.out.print("Indica la hoja y el rango: ");
        String range = getText();
        dataset = controlador.getData(dir, range);
        dataset.showParsedData();
    }

    public String getText() throws GeneralSecurityException, IOException {
        String str = null;
        try {
            str = in.readLine();
            return str;
        } catch(Exception e){
            System.out.print("Operación incorrecta. ");
            selectSheet();
            return null;
        }
    }
}
