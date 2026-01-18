package model;

import common.FuncionarioRegistravel;
import enumeration.Cargo;

public class Assistente extends FuncionarioRegistravel {
    public Assistente(String name) {
        super(name, Cargo.ASSISTENTE);
    }
}
