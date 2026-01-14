package common;

import model.RegistroHora;

public interface Registravel {
    void addRegistro(RegistroHora registro);
    void removeRegistro(RegistroHora registro);
    void displayRegistros();
}
