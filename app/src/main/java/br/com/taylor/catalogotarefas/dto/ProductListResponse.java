package br.com.taylor.catalogotarefas.dto;

import br.com.taylor.catalogotarefas.model.Product;
import lombok.Data;
import java.util.List;

@Data
public class ProductListResponse {
    private List<Product> products;
    private int total;
    private int skip;
    private int limit;
}