package ui;

import common.Funcionario;
import exception.FuncionarioNaoRegistravelException;
import exception.FuncionarioNotFoundException;
import factory.FuncionarioFactory;
import model.RegistroHora;
import repository.FuncionarioRepository;
import service.FuncionarioService;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static util.DateUtils.DATE_FORMATTER;
import static util.DateUtils.TIME_FORMATTER;

public class AppUI {
    private static Scanner sc = new Scanner(System.in);
    private static FuncionarioService service = new FuncionarioService(
            new FuncionarioRepository(),
            Clock.system(ZoneId.of("America/Sao_Paulo")));

    public static void mainMenu() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Registrar funcionário");
            System.out.println("2 - Registrar período de trabalho");
            System.out.println("3 - Listar funcionários");
            System.out.println("4 - Listar horas de trabalho registradas");
            System.out.println("0 - Sair");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> registration();
                case "2" -> addWorkHours();
                case "3" -> listAll();
                case "4" -> listRegistroHoras();
                case "0" -> {
                    System.out.println("Encerrando sistema...");
                    return;
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private static void listRegistroHoras() {
        System.out.print("ID do funcionário: ");
        String id = sc.nextLine().trim();
        service.getRegistroHoras(id).displayRegistros();
    }

    private static void listAll() {
        service.getAll().forEach(Funcionario::displayInfo);
    }

    private static void addWorkHours() {
        try {
            System.out.print("ID do funcionário: ");
            String id = sc.nextLine().trim();

            service.isRegistrable(service.getById(id));

            System.out.print("Dia (dd/MM/yyyy): ");
            String diaInput = sc.nextLine().trim();
            var dia = LocalDate.parse(diaInput, DATE_FORMATTER);

            System.out.print("Hora de entrada (HH:mm): ");
            String entradaInput = sc.nextLine().trim();
            var entrada = LocalTime.parse(entradaInput, TIME_FORMATTER);

            System.out.print("Hora de saída (HH:mm): ");
            String saidaInput = sc.nextLine().trim();
            var saida = LocalTime.parse(saidaInput, TIME_FORMATTER);

            RegistroHora registro = new RegistroHora(dia, entrada, saida);

            service.addRegistroHora(registro, id);
            System.out.println("Período registrado com sucesso!");

        } catch (IllegalArgumentException | FuncionarioNaoRegistravelException |FuncionarioNotFoundException e) {
            System.out.println("ERRO: " + e.getMessage());
        }catch (DateTimeParseException e) {
            System.out.println("Formato de data ou hora incorreto");
        }
    }

    private static void registration() {
        System.out.println("Escolha o tipo:");
        System.out.println("1 - Estagiário");
        System.out.println("2 - Assistente");
        System.out.println("3 - Coordenador");
        System.out.println("4 - Analista");
        System.out.println("5 - Gerente");

        String option = sc.nextLine().trim();

        System.out.print("Nome: ");
        String name = sc.nextLine().trim();
        if (name.isBlank()) {
            System.out.println("\"Nome não pode ser vazio\"");
            return;
        }

        try {
            registerFuncionario(option, name);
            System.out.println("Funcionario registrado com sucesso!");
        }catch (IllegalArgumentException exp) {
            System.out.println("ERRO: " + exp.getMessage());
        }
    }

    private static void registerFuncionario(String option, String name) {
        service.add(FuncionarioFactory.createFuncionario(option, name));
    }
}
