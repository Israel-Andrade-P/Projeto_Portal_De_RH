package tests;

import common.Funcionario;
import repository.FuncionarioRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeFuncionarioRepo extends FuncionarioRepository {
    private final Map<String, Funcionario> storage = new HashMap<>();

    @Override
    public void persistFuncionario(Funcionario funcionario) {
        storage.put(funcionario.getId(), funcionario);
    }

    @Override
    public Funcionario findById(String id) {
        return storage.get(id);
    }

    @Override
    public List<Funcionario> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }
}
