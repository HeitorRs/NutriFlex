package info.heitor.nutriflex.service;

import info.heitor.nutriflex.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {

    public List<Pedido> listarTodosPedidos();

    public Pedido salvarPedido(Pedido pedido);

    public Optional<Pedido> encontrarPedidoPorId(Long id);

    public void deletarPedidoPorId(Long id);

    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado);
}
