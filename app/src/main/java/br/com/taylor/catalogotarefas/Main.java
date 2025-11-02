package br.com.taylor.catalogotarefas;

import br.com.taylor.catalogotarefas.model.Product;
import br.com.taylor.catalogotarefas.model.Todo;
import br.com.taylor.catalogotarefas.service.ProductService;
import br.com.taylor.catalogotarefas.service.TodoService;
import br.com.taylor.catalogotarefas.util.ConsolePrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductService productService = new ProductService();
        TodoService todoService = new TodoService();

        // Lista de opções: cada item é um par (label, ação)
        List<RunnableMenuOption> options = new ArrayList<>();

        // 1. Listar produtos
        options.add(new RunnableMenuOption("Listar produtos", () -> {
            try {
                System.out.print("Quantos produtos deseja listar? ");
                int limit = Integer.parseInt(sc.nextLine());
                List<Product> produtos = productService.list(limit, 0);
                ConsolePrinter.printProducts(produtos);
            } catch (Exception e) {
                System.err.println("Erro ao listar produtos: " + e.getMessage());
            }
        }));

        // 2. Buscar produto por nome
        options.add(new RunnableMenuOption("Buscar produto por nome", () -> {
            try {
                System.out.print("Digite o nome do produto: ");
                String nome = sc.nextLine();
                List<Product> encontrados = productService.search(nome);
                ConsolePrinter.printProducts(encontrados);
            } catch (Exception e) {
                System.err.println("Erro ao buscar produto: " + e.getMessage());
            }
        }));

        // 3. Listar todos
        options.add(new RunnableMenuOption("Listar todos", () -> {
            try {
                List<Todo> todos = todoService.list(5, 0);
                ConsolePrinter.printTodos(todos);
            } catch (Exception e) {
                System.err.println("Erro ao listar todos: " + e.getMessage());
            }
        }));

        // 4. Adicionar todo
        options.add(new RunnableMenuOption("Adicionar todo", () -> {
            try {
                System.out.print("Digite a descrição do todo: ");
                String descricao = sc.nextLine();
                System.out.print("Digite o userId: ");
                int userId = Integer.parseInt(sc.nextLine());
                Todo novo = todoService.add(descricao, userId);
                System.out.println("Criado (simulado):");
                ConsolePrinter.printSingleTodo(novo);
            } catch (Exception e) {
                System.err.println("Erro ao adicionar todo: " + e.getMessage());
            }
        }));

        // 5. Alternar status de um todo
        options.add(new RunnableMenuOption("Alternar status de um todo", () -> {
            try {
                System.out.print("Digite o ID do todo existente: ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("Marcar como concluído? (true/false): ");
                boolean completed = Boolean.parseBoolean(sc.nextLine());
                Todo atualizado = todoService.toggle(id, completed);
                System.out.println("Atualizado:");
                ConsolePrinter.printSingleTodo(atualizado);
            } catch (Exception e) {
                System.err.println("Erro ao atualizar todo: " + e.getMessage());
            }
        }));

        // 6. Remover todo
        options.add(new RunnableMenuOption("Remover todo", () -> {
            try {
                System.out.print("Digite o ID do todo existente: ");
                int id = Integer.parseInt(sc.nextLine());
                boolean removido = todoService.delete(id);
                System.out.println("Removido? " + removido);
            } catch (Exception e) {
                System.err.println("Erro ao remover todo: " + e.getMessage());
            }
        }));

        // Loop principal
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== Catálogo de Tarefas ===");
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i).label());
            }
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            if (opcao > 0 && opcao <= options.size()) {
                options.get(opcao - 1).action().run();
            } else if (opcao == 0) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }

    // Record simples para armazenar label + ação
    record RunnableMenuOption(String label, Runnable action) {}
}