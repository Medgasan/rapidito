package org.example.cursospring.rapidito.web.controller;


import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaWebController {


    private final RestClient restClient;


    public ReservaWebController(RestClient restClient) {
        this.restClient = restClient;
    }


    @GetMapping("")
    public String main(){
        return "redirect:/reservas/";
    }


    @GetMapping("/")
    public String mostrarReservas(Model model){
        List<ReservaDTO> dtos = restClient.get()
                .uri("/reservas/")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ReservaDTO>>() {});
        model.addAttribute("reservas", dtos);
        return "listaReservas";
    }


    // Crear - obtener datos
    @GetMapping("/new")
    public String nuevoReserva(Model model){
        model.addAttribute("reserva", new ReservaDTO());
        model.addAttribute("editMode", true);
        return "reserva";
    }


    // Crear - guardar datos
    @PostMapping("/")
    public String guardarReserva(@ModelAttribute ReservaDTO reservaDTO){

        ReservaDTO dto = restClient.post()
                .uri("/reservas/")
                .body(reservaDTO)
                .retrieve()
                .body(ReservaDTO.class);

        return "redirect:/reservas/" + dto.getId();
    }


    // ver
    @GetMapping("/{id}")
    public String mostrarReserva(Model model, @PathVariable Long id){
        ReservaDTO dto = restClient.get()
                .uri("/reservas/{id}", id)
                .retrieve()
                .body(ReservaDTO.class);
        model.addAttribute("reserva", dto);
        model.addAttribute("editMode", false);
        return "reserva";
    }


    // editar/actualizar
    @GetMapping("/{id}/edit")
    public String editarReserva(Model model, @PathVariable Long id){
        ReservaDTO dto = restClient.get()
                .uri("/reservas/{id}", id)
                .retrieve()
                .body(ReservaDTO.class);
        model.addAttribute("reserva", dto);
        model.addAttribute("editMode", true);
        return "reserva";
    }


    // editar/actualizar
    @PostMapping("/{id}/edit")
    public String actualizarReserva(@PathVariable Long id, @ModelAttribute ReservaDTO reservaDTO){
        ReservaDTO dto = restClient.post()
                .uri("/reservas/{id}/edit", id)
                .body(reservaDTO)
                .retrieve()
                .body(ReservaDTO.class);
        return "redirect:/reservas/" + dto.getId();
    }


    // borrar
    @GetMapping("/{id}/delete")
    public String eliminarReserva(Model model, @PathVariable Long id){

        restClient.delete()
                .uri("/reservas/{id}/delete", id)
                .retrieve()
                .body(ReservaDTO.class);

        return "redirect:/reservas/";
    }



}