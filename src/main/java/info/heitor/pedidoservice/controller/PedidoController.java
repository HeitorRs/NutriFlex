package info.heitor.pedidoservice.controller;

import info.heitor.pedidoservice.model.Pedido;
import info.heitor.pedidoservice.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> addPedido(@RequestBody Pedido pedido) {
        BigDecimal valorTotal = pedidoService.valorTotalPedido(pedido);
        pedido.setValorTotal(valorTotal);
        Pedido saved = pedidoService.salvarPedido(pedido);
        return ResponseEntity.ok(saved);
    }
}
