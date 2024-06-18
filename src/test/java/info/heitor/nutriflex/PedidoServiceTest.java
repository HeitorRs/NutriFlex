package info.heitor.nutriflex;

import info.heitor.nutriflex.model.Pedido;
import info.heitor.nutriflex.service.PedidoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @Test
    @DisplayName("Deve salvar um pedido")
    public void testSalvarPedido() {
        Pedido pedido = new Pedido();
        pedido.setData(new Date());

        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);

        assertNotNull(pedidoSalvo.getId());
        assertEquals(pedido.getData(), pedidoSalvo.getData());
    }

    @Test
    @DisplayName("Deve encontrar um pedido pelo ID")
    public void testEncontrarPedidoPorId() {
        Pedido pedido = new Pedido();
        pedido.setData(new Date());
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);

        Optional<Pedido> pedidoEncontrado = pedidoService.encontrarPedidoPorId(pedidoSalvo.getId());

        assertTrue(pedidoEncontrado.isPresent());
        assertEquals(pedidoSalvo.getId(), pedidoEncontrado.get().getId());
    }

}
