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
    public String main(){
        return "redirect:/clientes/";
    }


    // mostrar todos
    @GetMapping("/")
    public String mostrarClientes(Model model){

        List<ClienteDTO> dto = restClient.get()
        .uri("/clientes/") // Esto llamará a http://localhost:8080/api/clientes/
        .retrieve()
        .body(new ParameterizedTypeReference<List<ClienteDTO>>() {});

        model.addAttribute("clientes", dto);
        return "listaClientes";
    }


    // Crear - obtener datos
    @GetMapping("/new")
    public String nuevoCliente(Model model){
        model.addAttribute("cliente", new  ClienteDTO());
        model.addAttribute("editMode", true);
        return "cliente";
    }


    // Crear - guardar datos
    @PostMapping("/")
    public String guardarCliente(@ModelAttribute ClienteDTO clienteDTO){

        ClienteDTO dto= restClient.post()
                .uri("/clientes/")
                .body(clienteDTO)
                .retrieve()
                .body(clienteDTO.getClass());

        return "redirect:/cliente/" + dto.getId();
    }


    // ver
    @GetMapping("/{id}")
    public String mostrarCliente(Model model, @PathVariable Long id){

        ClienteDTO dto = restClient.get()
                .uri("/clientes/{id}", id) // Se añade el ID a la base: http://localhost:8080/api/{id}
                .retrieve()
                .body(ClienteDTO.class); // Convertimos el JSON recibido en objeto Java

        model.addAttribute("cliente", dto);
        model.addAttribute("editMode", false);

        return "cliente"; // Retorna la plantilla cliente.html
    }


    // editar/actualizar
    @GetMapping("/{id}/edit")
    public String editarCliente(Model model, @PathVariable Long id){

        ClienteDTO dto = restClient.get()
                .uri("/clientes/{id}", id) // Se añade el ID a la base: http://localhost:8080/api/{id}
                .retrieve()
                .body(ClienteDTO.class); // Convertimos el JSON recibido en objeto Java

        model.addAttribute("cliente", dto);

        model.addAttribute("editMode", true);
        return "cliente";
    }


    // editar/actualizar
    @PostMapping("/{id}/edit")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute ClienteDTO clienteDTO){

        ClienteDTO dto = restClient.post()
                .uri("/clientes/{id}/edit",id)
                .body(clienteDTO)
                .retrieve()
                .body(clienteDTO.getClass());

        return "redirect:/cliente/" + clienteDTO.getId();
    }


    // borrar
    @GetMapping("/{id}/delete")
    public String eliminarCliente(Model model, @PathVariable Long id){

        restClient.delete()
                .uri("/clientes/{id}/delete", id)
                .retrieve()
                .body(ClienteDTO.class);

        return "redirect:/";
    }


}
