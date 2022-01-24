/**
 * Main del programa
 * Carpeta del proyecto en Drive: https://drive.google.com/drive/folders/1lWYNeYqmL9OmMGqL2dvDoccfrqlwfMIx?usp=sharing
 **/

package MainDemo;

import Control.ControlSheets;
import Modelo.Sheet;
import Vista.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main (String[] args) throws GeneralSecurityException, IOException {
        // Comprobación del número de argumentos pasados
        if(args.length != 1) {
            System.err.print("El número de argumentos es incorrecto.\n"
                            + "-trm para vista desde terminal\n"
                            + "-grf para vista gráfica\n");
            return;
        }

        // Declaración de la vista
        IVista vista = null;

        // Selección del tipo de vista
        switch (args[0]) {
            case "-trm":
                vista = new Textual();
                break;
            case "-grf":
                vista = new Grafica();
                break;
            default:
                System.err.print("El argumento indicado es incorrecto.\n"
                        + "-trm para vista desde terminal\n"
                        + "-grf para vista gráfica\n");
        }
        // Modelo
        // data : "1xlZnv7S0f5VbAyIQbJWRBI3LGIrWNdJIYeNVENG5_oE","Hoja 1!A1:BJ"
        Sheet modelo = new Sheet();

        // Controlador
        ControlSheets controlador = new ControlSheets(vista, modelo);

        vista.setControlador(controlador);
        vista.start();
    }
}
