package Vista;

import Control.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface IVista {
    void setControlador(ControlSheets controlador);
    void start() throws GeneralSecurityException, IOException;
}
