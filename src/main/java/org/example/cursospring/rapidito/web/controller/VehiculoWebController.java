package org.example.cursospring.rapidito.web.controller;

import org.example.cursospring.rapidito.api.dto.VehiculoDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import java.util.List;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoWebController {

    private final RestClient restClient;

    public VehiculoWebController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("")
    public String main() {
        return "redirect:/vehiculos/";
    }

    @GetMapping("/")
    public String mostrarVehiculos(Model model) {
        List<VehiculoDTO> dtos = restClient.get()
                .uri("/vehiculos/")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        model.addAttribute("vehiculos", dtos);
        return "listaVehiculos";
    }

    @GetMapping("/list")
    public String mostrarVehiculosPorMarca(@RequestParam(required = false) String marca, Model model) {
        List<VehiculoDTO> dtos;
        if (marca == null || marca.isBlank()) {
            dtos = restClient.get().uri("/vehiculos/").retrieve()
                    .body(new ParameterizedTypeReference<>() {});
        } else {
            dtos = restClient.get().uri("/vehiculos/{marca}/list", marca).retrieve()
                    .body(new ParameterizedTypeReference<>() {});
        }
        model.addAttribute("marca", marca);
        model.addAttribute("vehiculos", dtos);
        return "listaVehiculos";
    }

    @GetMapping("/new")
    public String nuevoVehiculo(Model model) {
        model.addAttribute("vehiculo", new VehiculoDTO());
        model.addAttribute("editMode", true);
        return "vehiculo";
    }

    @PostMapping("/")
    public String guardarVehiculo(@ModelAttribute VehiculoDTO vehiculoDTO) {
        restClient.post()
                .uri("/vehiculos/")
                .body(vehiculoDTO)
                .retrieve()
                .body(VehiculoDTO.class);
        return "redirect:/vehiculos/";
    }

    @GetMapping("/{id}")
    public String mostrarVehiculo(Model model, @PathVariable Long id) {
        VehiculoDTO dto = restClient.get()
                .uri("/vehiculos/{id}", id)
                .retrieve()
                .body(VehiculoDTO.class);
        model.addAttribute("vehiculo", dto);
        model.addAttribute("editMode", false);
        return "vehiculo";
    }

    @GetMapping("/{id}/edit")
    public String editarVehiculo(Model model, @PathVariable Long id) {
        VehiculoDTO dto = restClient.get()
                .uri("/vehiculos/{id}", id)
                .retrieve()
                .body(VehiculoDTO.class);
        model.addAttribute("vehiculo", dto);
        model.addAttribute("editMode", true);
        return "vehiculo";
    }

    @PostMapping("/{id}/edit")
    public String actualizarVehiculo(@PathVariable Long id, @ModelAttribute VehiculoDTO vehiculoDTO) {
        VehiculoDTO dto = restClient.patch()
                .uri("/vehiculos/{id}", id)
                .body(vehiculoDTO)
                .retrieve()
                .body(VehiculoDTO.class);
        return "redirect:/vehiculos/" + dto.getId();
    }

    @GetMapping("/{id}/delete")
    public String eliminarVehiculo(@PathVariable Long id) {
        restClient.delete()
                .uri("/vehiculos/{id}", id)
                .retrieve()
                .toBodilessEntity();
        return "redirect:/vehiculos/";
    }
}
