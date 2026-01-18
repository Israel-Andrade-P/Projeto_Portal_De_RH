package model;

import common.Funcionario;
import enumeration.Cargo;

public class Gerente extends Funcionario {
    public Gerente(String name) {
        super(name, Cargo.GERENTE);
    }
}
