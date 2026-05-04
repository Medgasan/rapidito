package org.example.cursospring.rapidito.controller;


import org.example.cursospring.rapidito.dto.ClienteDTO;
import org.example.cursospring.rapidito.entity.Cliente;
import org.example.cursospring.rapidito.mappers.ClienteMapper;
import org.example.cursospring.rapidito.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {


    private final IClienteService clienteService;


    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }


    // mostrar todos
    @GetMapping("/")
    public String mostrarClientes(Model model){
        model.addAttribute("clientes", clienteService.mostrarClientes());
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
    @PostMapping("/{id}")
    public String guardarCliente(@ModelAttribute ClienteDTO clienteDTO){
        clienteDTO = clienteService.crearCliente(clienteDTO);
        return "redirect:/cliente/" + clienteDTO.getId();
    }


    // ver
    @GetMapping("/id")
    public String mostrarCliente(Model model, @PathVariable Long id){
        model.addAttribute("cliente", clienteService.mostrarCliente(id));
        model.addAttribute("editMode", false);
        return "cliente";
    }


    // editar/actualizar
    @GetMapping("/id/edit")
    public String editarCliente(Model model, @PathVariable Long id){
        model.addAttribute("cliente", clienteService.mostrarCliente(id));
        model.addAttribute("editMode", true);
        return "cliente";
    }


    // editar/actualizar
    @PostMapping("/id/edit")
    public String actualizarCliente(@ModelAttribute ClienteDTO clienteDTO){
        clienteDTO = clienteService.actualizarCliente(clienteDTO);
        return "redirect:/cliente/" + clienteDTO.getId();
    }


    // borrar
    @GetMapping("/{id}/delete")
    public String eliminarCliente(Model model, @PathVariable Long id){
        boolean resultado = clienteService.eliminarCliente(clienteService.mostrarCliente(id));
        model.addAttribute("resultado", resultado);
        return "redirect:/";
    }


}
