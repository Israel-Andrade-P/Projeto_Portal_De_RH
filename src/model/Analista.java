package model;

import common.FuncionarioRegistravel;
import enumeration.Cargo;

public class Analista extends FuncionarioRegistravel {

    public Analista(String name) {
        super(name, Cargo.ANALISTA);
    }
}
