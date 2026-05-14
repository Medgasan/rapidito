package org.example.cursospring.rapidito.web.controller;


import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
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
        getCommon(model);
        model.addAttribute("contrato", new ContratoDTO());
        model.addAttribute("editMode", true);
        return "contrato";
    }


    // Crear - obtener datos
    @GetMapping("/{id}/new")
    public String nuevoContratoDesdeReserva(@PathVariable long id, Model model){

        ReservaDTO reserva = restClient.get()
                .uri("/reservas/{id}",id)
                .retrieve()
                .body(ReservaDTO.class);

        ContratoDTO contratoDTO = new ContratoDTO();
        contratoDTO.setCliente(reserva.getCliente());
        contratoDTO.setVehiculo(reserva.getVehiculo());
        contratoDTO.setFechaInicio(reserva.getFechaInicio());
        contratoDTO.setFechaFin(reserva.getFechaFin());

        getCommon(model);
        model.addAttribute("contrato", contratoDTO);
        model.addAttribute("editMode", true);
        return "contrato";
    }


    // Crear - guardar datos
    @PostMapping("/")
    public String guardarContrato(@ModelAttribute ContratoDTO contratoDTO){

        ContratoDTO dto = restClient.put()
                .uri("/contratos/")
                .body(contratoDTO)
                .retrieve()
                .body(ContratoDTO.class);

        return "redirect:/contratos/";
    }


    // ver
    @GetMapping("/{id}")
    public String mostrarContrato(Model model, @PathVariable Long id){

        ContratoDTO dto = restClient.get()
                .uri("/contratos/{id}", id)
                .retrieve()
                .body(ContratoDTO.class);

        getCommon(model);
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

        getCommon(model);
        model.addAttribute("contrato", dto);
        model.addAttribute("editMode", false);

        return "contrato";
    }


    // editar/actualizar
    @PostMapping("/{id}/edit")
    public String actualizarContrato(@PathVariable Long id, @ModelAttribute ContratoDTO contratoDTO){
        ContratoDTO dto = restClient.patch()
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


    private void getCommon(Model model){
        List<ClienteDTO> clientes = restClient.get()
                .uri("/clientes/")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ClienteDTO>>() {});

        List<VehiculoDTO> vehiculos = restClient.get()
                .uri("/vehiculos/")
                .retrieve()
                .body(new ParameterizedTypeReference<List<VehiculoDTO>>() {});
        model.addAttribute("clientes", clientes);
        model.addAttribute("vehiculos", vehiculos);
    }

}
