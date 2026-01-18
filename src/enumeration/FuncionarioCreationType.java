package enumeration;

import common.Funcionario;
import model.*;

import java.util.Arrays;
import java.util.function.Function;

public enum FuncionarioCreationType {
    ESTAGIARIO("1", Estagiario::new),
    ASSITENTE("2", Assistente::new),
    COORDENADOR("3", Coordenador::new),
    ANALISTA("4", Analista::new),
    GERENTE("5", Gerente::new);

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
