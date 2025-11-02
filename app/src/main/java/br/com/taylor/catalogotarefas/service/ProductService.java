package br.com.taylor.catalogotarefas.service;

import br.com.taylor.catalogotarefas.dto.ProductListResponse;
import br.com.taylor.catalogotarefas.model.Product;
import br.com.taylor.catalogotarefas.http.HttpClientUtil;

import java.util.List;

public class ProductService {
    private static final String BASE_URL = "https://dummyjson.com/products";

    public List<Product> list(int limit, int skip) throws Exception {
        String url = BASE_URL + "?limit=" + limit + "&skip=" + skip;
        ProductListResponse resp = HttpClientUtil.get(url, ProductListResponse.class);
        return resp.getProducts();
    }

    public List<Product> search(String q) throws Exception {
        String url = BASE_URL + "/search?q=" + q;
        ProductListResponse resp = HttpClientUtil.get(url, ProductListResponse.class);
        return resp.getProducts();
    }
}