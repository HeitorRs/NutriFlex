package info.heitor.pedidoservice.service;

import info.heitor.pedidoservice.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    public Produto findById(Long id) {
        RestClient restClient = RestClient.create();
        var serverUrl = String.format("http://localhost:8080/produtos/%d", id);
        return restClient.get().uri(serverUrl).retrieve().toEntity(Produto.class).getBody();
    }
}
