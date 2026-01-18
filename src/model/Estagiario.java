package model;

import common.Funcionario;
import enumeration.Cargo;

public class Estagiario extends Funcionario {
    public Estagiario(String name) {
        super(name, Cargo.ESTAGIARIO);
    }
}
