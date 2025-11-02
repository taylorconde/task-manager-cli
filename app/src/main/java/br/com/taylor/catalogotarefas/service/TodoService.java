package br.com.taylor.catalogotarefas.service;

import br.com.taylor.catalogotarefas.dto.TodoListResponse;
import br.com.taylor.catalogotarefas.http.HttpClientUtil;
import br.com.taylor.catalogotarefas.model.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TodoService {
    private static final String BASE_URL = "https://dummyjson.com/todos";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public List<Todo> list(int limit, int skip) throws Exception {
        String url = BASE_URL + "?limit=" + limit + "&skip=" + skip;
        TodoListResponse resp = HttpClientUtil.get(url, TodoListResponse.class);
        return resp.getTodos();
    }

    public Todo add(String text, int userId) throws Exception {
        String body = String.format("{\"todo\":\"%s\",\"userId\":%d}", text, userId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/add"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() / 100 != 2) {
            throw new RuntimeException("Erro ao adicionar todo: " + response.body());
        }

        return mapper.readValue(response.body(), Todo.class);
    }

    public Todo toggle(int id, boolean completed) throws Exception {
        String body = String.format("{\"completed\":%s}", completed);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() / 100 != 2) {
            throw new RuntimeException("Erro ao atualizar todo: " + response.body());
        }

        return mapper.readValue(response.body(), Todo.class);
    }

    public boolean delete(int id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() / 100 != 2) {
            throw new RuntimeException("Erro ao deletar todo: " + response.body());
        }

        return response.body().contains("\"isDeleted\":true");
    }
}