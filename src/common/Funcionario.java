package common;

import enumeration.Cargo;
import util.FuncionarioUtils;

public abstract class Funcionario {
    private final String id;
    private final String name;
    private final Cargo cargo;

    public Funcionario(String name, Cargo cargo) {
        this.name = name;
        this.cargo = cargo;
        id = FuncionarioUtils.generateId();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void displayInfo() {
        System.out.printf("ID: %s\nNome: %s\nCargo: %s\n", this.getId(), this.getName(), this.getCargo().getLabel());
    }
}
