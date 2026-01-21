package model;

import common.FuncionarioRegistravel;
import enumeration.Cargo;

import java.time.Duration;

public class Coordenador extends FuncionarioRegistravel {
    private static final Duration HORAS_EXTRAS = Duration.ofHours(5);

    public Coordenador(String name) {
        super(name, Cargo.COORDENADOR, HORAS_EXTRAS);
    }
}
