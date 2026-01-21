package model;

import common.FuncionarioRegistravel;
import enumeration.Cargo;

import java.time.Duration;

public class Analista extends FuncionarioRegistravel {
    private static final Duration HORAS_EXTRAS = Duration.ofHours(3);

    public Analista(String name) {
        super(name, Cargo.ANALISTA, HORAS_EXTRAS);
    }
}
