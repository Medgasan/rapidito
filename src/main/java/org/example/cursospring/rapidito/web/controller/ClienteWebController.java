package org.example.cursospring.rapidito.web.controller;

import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteWebController {

    private final RestClient restClient;

    public ClienteWebController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("")
    public String main() {
        return "redirect:/clientes/";
    }

    @GetMapping("/")
    public String mostrarClientes(Model model) {
        List<ClienteDTO> dtos = restClient.get()
                .uri("/clientes/")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        model.addAttribute("clientes", dtos);
        return "listaClientes";
    }

    @GetMapping("/new")
    public String nuevoCliente(Model model) {
        model.addAttribute("cliente", new ClienteDTO());
        model.addAttribute("editMode", true);
        return "cliente";
    }

    @PostMapping("/")
    public String guardarCliente(@ModelAttribute ClienteDTO clienteDTO) {
        ClienteDTO dto = restClient.post()
                .uri("/clientes/")
                .body(clienteDTO)
                .retrieve()
                .body(ClienteDTO.class);
        return "redirect:/clientes/" + dto.getId();
    }

    @GetMapping("/{id}")
    public String mostrarCliente(Model model, @PathVariable Long id) {
        ClienteDTO dto = restClient.get()
                .uri("/clientes/{id}", id)
                .retrieve()
                .body(ClienteDTO.class);
        model.addAttribute("cliente", dto);
        model.addAttribute("editMode", false);
        return "cliente";
    }

    @GetMapping("/{id}/edit")
    public String editarCliente(Model model, @PathVariable Long id) {
        ClienteDTO dto = restClient.get()
                .uri("/clientes/{id}", id)
                .retrieve()
                .body(ClienteDTO.class);
        model.addAttribute("cliente", dto);
        model.addAttribute("editMode", true);
        return "cliente";
    }

    @PostMapping("/{id}/edit")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute ClienteDTO clienteDTO) {
        ClienteDTO dto = restClient.patch()
                .uri("/clientes/{id}", id)
                .body(clienteDTO)
                .retrieve()
                .body(ClienteDTO.class);
        return "redirect:/clientes/" + dto.getId();
    }

    @GetMapping("/{id}/delete")
    public String eliminarCliente(@PathVariable Long id) {
        restClient.delete()
                .uri("/clientes/{id}", id)
                .retrieve()
                .toBodilessEntity();
        return "redirect:/clientes/";
    }
}
