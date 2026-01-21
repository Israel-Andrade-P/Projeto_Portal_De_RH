package model;

import common.FuncionarioRegistravel;
import enumeration.Cargo;

import java.time.Duration;

public class Assistente extends FuncionarioRegistravel {
    private static final Duration HORAS_EXTRAS = Duration.ofHours(3);

    public Assistente(String name) {
        super(name, Cargo.ASSISTENTE, HORAS_EXTRAS);
    }
}
