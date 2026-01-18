package factory;

import common.Funcionario;
import enumeration.FuncionarioCreationType;

public class FuncionarioFactory {
    public static Funcionario createFuncionario(String option, String name) {
        return FuncionarioCreationType.fromOption(option).create(name);
    }
}
