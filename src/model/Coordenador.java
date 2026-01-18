package model;

import common.FuncionarioRegistravel;
import enumeration.Cargo;

public class Coordenador extends FuncionarioRegistravel {
    public Coordenador(String name) {
        super(name, Cargo.COORDENADOR);
    }
}
