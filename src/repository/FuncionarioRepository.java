package repository;

import common.Funcionario;
import exception.FuncionarioNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {
    private final List<Funcionario> funcionarios = new ArrayList<>();

    public FuncionarioRepository() {}

    public void persistFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void deleteById(String id) {
        funcionarios.remove(getById(id));
    }

    public Funcionario findById(String id) {
        return getById(id);
    }

    public List<Funcionario> findAll() {
        return funcionarios;
    }

    private Funcionario getById(String id) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new FuncionarioNotFoundException(String.format("Funcionário com ID %s não existe", id)));
    }
}
