package common;

import enumeration.Cargo;
import model.RegistroHora;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import static constant.Constants.DATE_FORMATTER;
import static constant.Constants.TIME_FORMATTER;

public abstract class FuncionarioRegistravel extends Funcionario implements Registravel{
    private static final Duration CARGA_HORARIA_BASE = Duration.ofHours(9);
    private Set<RegistroHora> horas = new HashSet<>();
    private Duration horasExtras;

    public FuncionarioRegistravel(String name, Cargo cargo, Duration horasExtras) {
        super(name, cargo);
        this.horasExtras = horasExtras;
    }

    public Set<RegistroHora> getRegistroHoras() {
        return horas;
    }

    public Duration getCargaHorariaBase() {
        return CARGA_HORARIA_BASE;
    }

    public Duration getHorasExtras() {
        return horasExtras;
    }

    public Duration getDuracaoMaximaPermitida() {
        return CARGA_HORARIA_BASE.plus(getHorasExtras());
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
                    registro.getDia().format(DATE_FORMATTER),
                    registro.getHoraEntrada().format(TIME_FORMATTER),
                    registro.getHoraSaida().format(TIME_FORMATTER));
        });
    }
}
