package org.example.cursospring.rapidito.web.controller;


import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@Controller
@RequestMapping("/contratos")
public class ContratoWebController {

    private final RestClient restClient;


    public ContratoWebController(RestClient restClient) {
        this.restClient = restClient;
    }


    @GetMapping("")
    public String main(){
        return "redirect:/contratos/";
    }


    @GetMapping("/")
    public String mostrarContratos(Model model) {
        List<ContratoDTO> dtos = restClient.get()
                .uri("/contratos/")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ContratoDTO>>() {});
        model.addAttribute("contratos", dtos);
        return "listaContratos";
    }


    // Crear - obtener datos
    @GetMapping("/new")
    public String nuevoContrato(Model model){
        model.addAttribute("contrato", new ContratoDTO());
        model.addAttribute("editMode", true);
        return "contrato";
    }


    // Crear - guardar datos
    @PostMapping("/")
    public String guardarContrato(@ModelAttribute ContratoDTO contratoDTO){

        ContratoDTO dto = restClient.post()
                .uri("/contratos/")
                .body(contratoDTO)
                .retrieve()
                .body(ContratoDTO.class);

        return "redirect:/contratos/" + dto.getId();
    }


    // ver
    @GetMapping("/{id}")
    public String mostrarContrato(Model model, @PathVariable Long id){

        ContratoDTO dto = restClient.get()
                .uri("/contratos/{id}", id)
                .retrieve()
                .body(ContratoDTO.class);

        model.addAttribute("contrato", dto);
        model.addAttribute("editMode", false);
        return "contrato";
    }


    // editar/actualizar
    @GetMapping("/{id}/edit")
    public String editarContrato(Model model, @PathVariable Long id){

        ContratoDTO dto = restClient.get()
                .uri("/contratos/{id}", id)
                .retrieve()
                .body(ContratoDTO.class);

        model.addAttribute("contrato", dto);
        model.addAttribute("editMode", true);
        return "contrato";
    }


    // editar/actualizar
    @PostMapping("/{id}/edit")
    public String actualizarContrato(@PathVariable Long id, @ModelAttribute ContratoDTO contratoDTO){
        ContratoDTO dto = restClient.post()
                .uri("/contratos/{id}/edit", id)
                .body(contratoDTO)
                .retrieve()
                .body(ContratoDTO.class);
        return "redirect:/contratos/" + dto.getId();
    }


    // borrar
    @GetMapping("/{id}/delete")
    public String eliminarContrato(Model model, @PathVariable Long id){

        restClient.delete()
                .uri("/contratos/{id}/delete", id)
                .retrieve()
                .body(ContratoDTO.class);

        return "redirect:/contratos/";
    }

}
