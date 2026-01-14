package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroHora {
    private LocalDate dia;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    public RegistroHora(LocalDate dia, LocalTime horaEntrada, LocalTime horaSaida) {
        this.dia = dia;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    public LocalDate getDia() {
        return dia;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }
}
