package service;

import common.Funcionario;
import common.FuncionarioRegistravel;
import exception.FuncionarioNaoRegistravelException;
import model.RegistroHora;
import repository.FuncionarioRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class FuncionarioService {
    private final FuncionarioRepository repository;
    private final Clock clock;

    public FuncionarioService(FuncionarioRepository repository, Clock clock) {
        this.repository = repository;
        this.clock = clock;
    }

    public void add(Funcionario funcionario) {
        repository.persistFuncionario(funcionario);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Funcionario getById(String id) {
        return repository.findById(id);
    }

    public List<Funcionario> getAll() {
        return repository.findAll();
    }

    public void addRegistroHora(RegistroHora registro, String id) {
        FuncionarioRegistravel funcionario = validateRegistroHora(registro, id);
        funcionario.addRegistro(registro);
    }

    public FuncionarioRegistravel getRegistroHoras(String id) {
        var func = repository.findById(id);
        return isRegistrable(func);
    }

    private FuncionarioRegistravel validateRegistroHora(RegistroHora registro, String id) {
        LocalDate hojeSP = LocalDate.now(clock);
        if (registro.getDia().isBefore(hojeSP)) throw new IllegalArgumentException("Não é possível registrar dias do passado");
        if (!registro.getHoraEntrada().isBefore(registro.getHoraSaida())) throw new IllegalArgumentException("Hora de Entrada deve ser antes da Hora de Saída");
        Funcionario funcionario = repository.findById(id);
        return isRegistrable(funcionario);
    }
    private FuncionarioRegistravel isRegistrable(Funcionario funcionario) {
        if (!(funcionario instanceof FuncionarioRegistravel registravel)) throw new FuncionarioNaoRegistravelException("Funcionário não bate ponto!");
        return registravel;
    }
}
