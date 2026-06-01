package org.example.cursospring.rapidito.web.controller;

import org.example.cursospring.rapidito.api.dto.ClienteDTO;
import org.example.cursospring.rapidito.api.dto.ReservaDTO;
import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
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
    public String main() {
        return "redirect:/reservas/";
    }

    @GetMapping("/")
    public String mostrarReservas(Model model) {
        List<ReservaDTO> dtos = restClient.get()
                .uri("/reservas/")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        model.addAttribute("reservas", dtos);
        return "listaReservas";
    }

    @GetMapping("/new")
    public String nuevoReserva(Model model) {
        getCommon(model);
        model.addAttribute("reserva", new ReservaDTO());
        model.addAttribute("editMode", true);
        return "reserva";
    }

    @PostMapping("/")
    public String guardarReserva(@ModelAttribute ReservaDTO reservaDTO) {
        ReservaDTO dto = restClient.post()
                .uri("/reservas/")
                .body(reservaDTO)
                .retrieve()
                .body(ReservaDTO.class);
        return "redirect:/reservas/" + dto.getId();
    }

    @GetMapping("/{id}")
    public String mostrarReserva(Model model, @PathVariable Long id) {
        ReservaDTO dto = restClient.get().uri("/reservas/{id}", id).retrieve().body(ReservaDTO.class);
        getCommon(model);
        model.addAttribute("reserva", dto);
        model.addAttribute("editMode", false);
        return "reserva";
    }

    @GetMapping("/{id}/edit")
    public String editarReserva(Model model, @PathVariable Long id) {
        ReservaDTO dto = restClient.get().uri("/reservas/{id}", id).retrieve().body(ReservaDTO.class);
        getCommon(model);
        model.addAttribute("reserva", dto);
        model.addAttribute("editMode", true);
        return "reserva";
    }

    @PostMapping("/{id}/edit")
    public String actualizarReserva(@PathVariable Long id, @ModelAttribute ReservaDTO reservaDTO) {
        restClient.patch()
                .uri("/reservas/{id}", id)
                .body(reservaDTO)
                .retrieve()
                .body(ReservaDTO.class);
        return "redirect:/reservas/";
    }

    @GetMapping("/{id}/delete")
    public String eliminarReserva(@PathVariable Long id) {
        restClient.delete().uri("/reservas/{id}", id).retrieve().toBodilessEntity();
        return "redirect:/reservas/";
    }


    @GetMapping("/{id}/cancelar")
    public String cancelarReserva(@PathVariable Long id) {
        restClient.patch().uri("/reservas/{id}/cancelar", id).retrieve().body(ReservaDTO.class);
        return "redirect:/reservas/";
    }


    // Metodo para cargar datos comunes (clientes y vehículos) en el modelo
    private void getCommon(Model model) {
        model.addAttribute("clientes", restClient.get().uri("/clientes/").retrieve()
                .body(new ParameterizedTypeReference<List<ClienteDTO>>() {}));
        model.addAttribute("vehiculos", restClient.get().uri("/vehiculos/").retrieve()
                .body(new ParameterizedTypeReference<List<VehiculoDTO>>() {}));
    }

    //Todo: Implementar filtro de Reserva de la Api

}
