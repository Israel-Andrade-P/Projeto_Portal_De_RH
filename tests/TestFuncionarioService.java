import common.Funcionario;
import exception.FuncionarioNaoRegistravelException;
import model.Assistente;
import model.Gerente;
import model.RegistroHora;
import service.FuncionarioService;
import tests.FakeFuncionarioRepo;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class TestFuncionarioService {
    static Clock fixedClock = Clock.fixed(
            LocalDate.of(2026, 1, 21)
                    .atStartOfDay(ZoneId.of("America/Sao_Paulo"))
                    .toInstant(),
            ZoneId.of("America/Sao_Paulo")
    );

    public static void main(String[] args) {
        testRegistroHoraValido();
        testRegistroHoraMenorQueCargaBase();
        testRegistroHoraExcedeHorasExtras();
        testFuncionarioNaoRegistravel();

        System.out.println("✅ All tests passed!");
    }

    static void testRegistroHoraValido() {
        var repo = new tests.FakeFuncionarioRepo();
        var clock = fixedClock;
        var service = new FuncionarioService(repo, clock);

        var funcionario = new Assistente("João");
        service.add(funcionario);

        RegistroHora registro = new RegistroHora(
                LocalDate.now(clock),
                LocalTime.of(8, 0),
                LocalTime.of(17, 0) // 9h
        );

        service.addRegistroHora(registro, funcionario.getId());

        assert service.getRegistroHoras(funcionario.getId())
                .getRegistroHoras()
                .size() == 1;
    }

    static void testRegistroHoraMenorQueCargaBase() {
        var repo = new tests.FakeFuncionarioRepo();
        var service = new FuncionarioService(repo, fixedClock);

        var funcionario = new Assistente("Maria");
        service.add(funcionario);

        RegistroHora registro = new RegistroHora(
                LocalDate.now(fixedClock),
                LocalTime.of(8, 0),
                LocalTime.of(16, 0) // 8h
        );

        try {
            service.addRegistroHora(registro, funcionario.getId());
            assert false : "Expected exception not thrown";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("pelo menos 9 horas");
        }
    }

    static void testRegistroHoraExcedeHorasExtras() {
        var repo = new FakeFuncionarioRepo();
        var service = new FuncionarioService(repo, fixedClock);

        var funcionario = new Assistente("Carlos"); // +3h extra
        service.add(funcionario);

        RegistroHora registro = new RegistroHora(
                LocalDate.now(fixedClock),
                LocalTime.of(6, 0),
                LocalTime.of(19, 0) // 13h (9 + 3 = 12 max)
        );

        try {
            service.addRegistroHora(registro, funcionario.getId());
            assert false : "Expected exception not thrown";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("excede o limite");
        }
    }

    static void testFuncionarioNaoRegistravel() {
        var repo = new FakeFuncionarioRepo();
        var service = new FuncionarioService(repo, fixedClock);

        Funcionario gerente = new Gerente("Ana"); // not registrable
        service.add(gerente);

        RegistroHora registro = new RegistroHora(
                LocalDate.now(fixedClock),
                LocalTime.of(8, 0),
                LocalTime.of(17, 0)
        );

        try {
            service.addRegistroHora(registro, gerente.getId());
            assert false : "Expected FuncionarioNaoRegistravelException";
        } catch (FuncionarioNaoRegistravelException e) {
            assert true;
        }
    }

}
