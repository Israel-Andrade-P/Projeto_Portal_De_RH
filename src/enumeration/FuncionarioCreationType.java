package enumeration;

import common.Funcionario;
import model.*;

import java.util.Arrays;
import java.util.function.Function;

public enum FuncionarioCreationType {
    ESTAGIARIOOBJ("1", Estagiario::new),
    ASSISTENTEOBJ("2", Assistente::new),
    COORDENADOROBJ("3", Coordenador::new),
    ANALISTAOBJ("4", Analista::new),
    GERENTEOBJ("5", Gerente::new);

    private final String option;
    private final Function<String, Funcionario> creator;

    FuncionarioCreationType(String option, Function<String, Funcionario> creator) {
        this.option = option;
        this.creator = creator;
    }

    public String getOption() {
        return option;
    }

    public Funcionario create(String name) {
        return creator.apply(name);
    }

    public static FuncionarioCreationType fromOption(String option) {
        return Arrays.stream(FuncionarioCreationType.values())
                .filter(fct -> fct.getOption().equals(option))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Opção inválida"));
    }
}
