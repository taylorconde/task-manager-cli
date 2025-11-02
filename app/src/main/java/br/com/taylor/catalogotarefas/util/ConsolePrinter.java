package br.com.taylor.catalogotarefas.util;

import br.com.taylor.catalogotarefas.model.Product;
import br.com.taylor.catalogotarefas.model.Todo;

import java.util.List;

public class ConsolePrinter {

    public static void printProducts(List<Product> products) {
        System.out.println("\n=== Produtos ===");
        System.out.printf("%-5s %-40s %-10s%n", "ID", "Título", "Preço");
        System.out.println("---------------------------------------------------------------");
        for (Product p : products) {
            System.out.printf("%-5d %-40s $%-10.2f%n", p.getId(), p.getTitle(), p.getPrice());
        }
    }

    public static void printTodos(List<Todo> todos) {
        System.out.println("\n=== Todos ===");
        System.out.printf("%-5s %-60s %-10s %-10s%n", "ID", "Descrição", "Status", "User");
        System.out.println("--------------------------------------------------------------------------------");
        for (Todo t : todos) {
            String status = t.isCompleted() ? "OK" : "PEND";
            System.out.printf("%-5d %-60s %-10s %-10d%n",
                    t.getId(), t.getTodo(), status, t.getUserId());
        }
    }

    public static void printSingleTodo(Todo t) {
        String status = t.isCompleted() ? "OK" : "PEND";
        System.out.println(t.getId() + " | " + t.getTodo() + " | " + status + " | user=" + t.getUserId());
    }
}