package ui;

import common.Funcionario;
import exception.FuncionarioNaoRegistravelException;
import factory.FuncionarioFactory;
import model.RegistroHora;
import repository.FuncionarioRepository;
import service.FuncionarioService;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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

            System.out.print("Dia (dd/MM/yyyy): ");
            String diaInput = sc.nextLine().trim();

            System.out.print("Hora de entrada (HH:mm): ");
            String entradaInput = sc.nextLine().trim();

            System.out.print("Hora de saída (HH:mm): ");
            String saidaInput = sc.nextLine().trim();

            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter =
                    DateTimeFormatter.ofPattern("HH:mm");

            RegistroHora registro = new RegistroHora(
                    LocalDate.parse(diaInput, dateFormatter),
                    LocalTime.parse(entradaInput, timeFormatter),
                    LocalTime.parse(saidaInput, timeFormatter)
            );

            service.addRegistroHora(registro, id);
            System.out.println("Período registrado com sucesso!");

        } catch (IllegalArgumentException | FuncionarioNaoRegistravelException e) {
            System.out.println("ERRO: " + e.getMessage());
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
