package service;

import common.Funcionario;
import common.FuncionarioRegistravel;
import exception.FuncionarioNaoRegistravelException;
import model.RegistroHora;
import repository.FuncionarioRepository;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static constant.Constants.*;

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
        Funcionario funcionario = getById(id);
        isRegistrable(funcionario);
        var registravel = (FuncionarioRegistravel) funcionario;
        validateRegistroHora(registro, registravel);
        registravel.addRegistro(registro);
    }

    public FuncionarioRegistravel getRegistroHoras(String id) {
        Funcionario funcionario = getById(id);
        isRegistrable(funcionario);
        return  (FuncionarioRegistravel) funcionario;
    }

    private void validateRegistroHora(RegistroHora registro, FuncionarioRegistravel registravel) {
        LocalDate hojeSP = LocalDate.now(clock);
        if (registro.getDia().isBefore(hojeSP)) throw new IllegalArgumentException("Não é possível registrar dias do passado");
        if (registro.getHoraEntrada().isBefore(HORA_MINIMA_ENTRADA)) throw new IllegalArgumentException("Hora de Entrada não pode ser antes das 06:00");
        if (!registro.getHoraEntrada().isBefore(registro.getHoraSaida())) throw new IllegalArgumentException("Hora de Entrada deve ser antes da Hora de Saída");
        if (registro.getHoraSaida().isAfter(HORA_MAXIMA_SAIDA)) throw new IllegalArgumentException("Hora de Saída não pode ser depois das 22:00");

        Duration duration = Duration.between(registro.getHoraEntrada(), registro.getHoraSaida());
        if (duration.compareTo(registravel.getCargaHorariaBase()) < 0) throw new IllegalArgumentException("O período entre entrada e saída deve ser de pelo menos 9 horas");
        if (duration.compareTo(registravel.getDuracaoMaximaPermitida()) > 0) throw new IllegalArgumentException("O período de trabalho excede o limite permitido para este funcionário");
    }
    public void isRegistrable(Funcionario funcionario) {
        if (!(funcionario instanceof FuncionarioRegistravel)) throw new FuncionarioNaoRegistravelException("Funcionário não bate ponto!");
    }
}
