package info.heitor.nutriflex.controller;

import info.heitor.nutriflex.model.Pedido;
import info.heitor.nutriflex.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @Operation(summary = "Lista todos os pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso"),
    })
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        List<Pedido> pedidos = pedidoService.listarTodosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @Operation(summary = "Cria um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Pedido pedido) {
        try {
            Pedido novoPedido = pedidoService.salvarPedido(pedido);
            return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Encontra um pedido pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarPorId(@PathVariable Long id) {
        try {
            Optional<Pedido> pedido = pedidoService.encontrarPedidoPorId(id);
            if (pedido.isPresent()) {
                return new ResponseEntity<>(pedido, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Pedido não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Atualiza um pedido pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Pedido atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarPedido(id, pedido);
            return new ResponseEntity<>(pedidoAtualizado, HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Pedido não encontrado", HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Deleta um pedido pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Pedido excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
        try {
            pedidoService.deletarPedidoPorId(id);
            return new ResponseEntity<>("Pedido excluído com sucesso!", HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Pedido não encontrado", HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
