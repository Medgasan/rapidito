package org.example.cursospring.rapidito.web.controller;

import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    public String main() {
        return "redirect:/contratos/";
    }


    @GetMapping("/")
    public String mostrarContratos(Model model) {
        List<ContratoDTO> dtos = restClient.get()
                .uri("/contratos/")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        model.addAttribute("contratos", dtos);
        return "listaContratos";
    }

    @GetMapping("/new")
    public String nuevoContrato(Model model) {
        getCommon(model);
        model.addAttribute("contrato", new ContratoDTO());
        model.addAttribute("editMode", true);
        return "contrato";
    }


    @Transactional
    @GetMapping("/{id}/new")
    public String nuevoContratoDesdeReserva(@PathVariable long id, Model model) {
        ReservaDTO contrato = restClient.get().uri("/desde-reserva/{id}", id).retrieve().body(ReservaDTO.class);
        return "contrato";
    }


    @PostMapping("/")
    public String guardarContrato(@ModelAttribute ContratoDTO contratoDTO) {
        ContratoDTO dto = restClient.post()
                .uri("/contratos/")
                .body(contratoDTO)
                .retrieve()
                .body(ContratoDTO.class);
        return "redirect:/contratos/";
    }


    @GetMapping("/{id}")
    public String mostrarContrato(Model model, @PathVariable Long id) {
        ContratoDTO dto = restClient.get().uri("/contratos/{id}", id).retrieve().body(ContratoDTO.class);
        getCommon(model);
        model.addAttribute("contrato", dto);
        model.addAttribute("editMode", false);
        return "contrato";
    }


    @GetMapping("/{id}/edit")
    public String editarContrato(Model model, @PathVariable Long id) {
        ContratoDTO dto = restClient.get().uri("/contratos/{id}", id).retrieve().body(ContratoDTO.class);
        getCommon(model);
        model.addAttribute("contrato", dto);
        model.addAttribute("editMode", true);
        return "contrato";
    }


    @PostMapping("/{id}/edit")
    public String actualizarContrato(@PathVariable Long id, @ModelAttribute ContratoDTO contratoDTO) {
        ContratoDTO dto = restClient.patch()
                .uri("/contratos/{id}", id)
                .body(contratoDTO)
                .retrieve()
                .body(ContratoDTO.class);
        return "redirect:/contratos/" + dto.getId();
    }


    @GetMapping("/{id}/delete")
    public String eliminarContrato(@PathVariable Long id) {
        restClient.delete().uri("/contratos/{id}", id).retrieve().toBodilessEntity();
        return "redirect:/contratos/";
    }


    @GetMapping("contratos/{id}/cerrar")
    public String cerrarContrato(@PathVariable Long id) {
        restClient.patch().uri("/contratos/{id}/cerrar", id).retrieve().toBodilessEntity();
        return "redirect:/contratos/";
    }


    // Metodo para cargar datos comunes a varias vistas
    private void getCommon(Model model) {
        model.addAttribute("clientes", restClient.get().uri("/clientes/").retrieve()
                .body(new ParameterizedTypeReference<List<ClienteDTO>>() {}));
        model.addAttribute("vehiculos", restClient.get().uri("/vehiculos/").retrieve()
                .body(new ParameterizedTypeReference<List<VehiculoDTO>>() {}));
    }

    //Todo: Implementar filtro de contratos de la Api
}
