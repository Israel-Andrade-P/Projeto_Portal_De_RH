package common;

import enumeration.Cargo;
import model.RegistroHora;

import java.util.HashSet;
import java.util.Set;

public abstract class FuncionarioRegistravel extends Funcionario implements Registravel{
    private Set<RegistroHora> horas = new HashSet<>();

    public FuncionarioRegistravel(String name, Cargo cargo) {
        super(name, cargo);
    }

    public Set<RegistroHora> getRegistroHoras() {
        return horas;
    }

    @Override
    public void addRegistro(RegistroHora registro) {
        horas.add(registro);
    }

    @Override
    public void removeRegistro(RegistroHora registro) {
        horas.remove(registro);
    }

    @Override
    public void displayRegistros() {
        horas.forEach(registro -> {
            System.out.printf("Dia: %s\nEntrada: %s\nSaída: %s\n",
                    registro.getDia(),
                    registro.getHoraEntrada(),
                    registro.getHoraSaida());
        });
    }
}
