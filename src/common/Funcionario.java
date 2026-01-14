package common;

import util.FuncionarioUtils;

public abstract class Funcionario {
    private String id;
    private String name;

    public Funcionario(String name) {
        this.name = name;
        id = FuncionarioUtils.generateId();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
