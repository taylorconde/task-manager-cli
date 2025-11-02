package br.com.taylor.catalogotarefas.dto;

import br.com.taylor.catalogotarefas.model.Todo;
import lombok.Data;
import java.util.List;

@Data
public class TodoListResponse {
    private List<Todo> todos;
    private int total;
    private int skip;
    private int limit;
}