package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.Pedido;
import info.heitor.nutriflex.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService{

    private final PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> listarTodosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> encontrarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public void deletarPedidoPorId(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id).map(pedidoExistente -> {
            pedidoExistente.setData(pedidoAtualizado.getData());
            pedidoExistente.setProdutos(pedidoAtualizado.getProdutos());
            return pedidoRepository.save(pedidoExistente);
        }).orElseThrow(() -> new NoSuchElementException("Pedido de ID: " + id + " não encontrado"));
    }
}
