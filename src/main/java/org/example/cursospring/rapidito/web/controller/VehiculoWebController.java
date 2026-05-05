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
    public String main(){
        return "redirect:/vehiculos/";
    }

    // mostrar todos
    @GetMapping("/")
    public String mostrarVehiculos(Model model){
        List<VehiculoDTO> dtos = restClient.get()
            .uri("/vehiculos/")
            .retrieve()
            .body(new ParameterizedTypeReference<List<VehiculoDTO>>(){});
        model.addAttribute("vehiculos", dtos);
        return "listaVehiculos";
    }


    // mostrar por marca
    @GetMapping("/{marca}/list")
    public String mostrarVehiculosPorMarca(@PathVariable String marca, Model model){
        List<VehiculoDTO> dtos = restClient.get()
                .uri("/vehiculos/{marca}/list/", marca)
                .retrieve()
                .body(new ParameterizedTypeReference<List<VehiculoDTO>>(){});
        model.addAttribute("vehiculos", dtos);
        model.addAttribute("marca", marca);
        model.addAttribute("vehiculos", dtos);
        return "listaVehiculos";
    }


    // Crear - obtener datos
    @GetMapping("/new")
    public String nuevoVehiculo(Model model){
        model.addAttribute("vehiculo", new VehiculoDTO());
        model.addAttribute("editMode", true);
        return "vehiculo";
    }


    // Crear - guardar datos
    @PostMapping("/{id}")
    public String guardarVehiculo(@PathVariable Long id, @ModelAttribute VehiculoDTO vehiculoDTO){

        VehiculoDTO dto = restClient.post()
                .uri("/vehiculos/{id}", id)
                .body(vehiculoDTO)
                .retrieve()
                .body(VehiculoDTO.class);

        return "redirect:/vehiculo/" + vehiculoDTO.getId();
    }


    // ver
    @GetMapping("/{id}")
    public String mostrarVehiculo(Model model, @PathVariable Long id){

        VehiculoDTO dto = restClient.get()
                .uri("/vehiculos/{id}", id)
                .retrieve()
                .body(VehiculoDTO.class);
        model.addAttribute("vehiculo", dto);
        model.addAttribute("editMode", false);
        return "vehiculo";
    }


    // editar/actualizar
    @GetMapping("/{id}/edit")
    public String editarVehiculo(Model model, @PathVariable Long id){

        VehiculoDTO dto = restClient.get()
                .uri("/vehiculos/{id}", id)
                .retrieve()
                .body(VehiculoDTO.class);
        model.addAttribute("vehiculo", dto);
        model.addAttribute("editMode", true);
        return "vehiculo";
    }


    // editar/actualizar
    @PostMapping("/{id}/edit")
    public String actualizarVehiculo(@PathVariable long id, @ModelAttribute VehiculoDTO vehiculoDTO){

        VehiculoDTO dto = restClient.post()
                .uri("/vehiculos/{id}/edit", id)
                .body(vehiculoDTO)
                .retrieve()
                .body(VehiculoDTO.class);

        //vehiculoDTO = vehiculoService.actualizarVehiculo(vehiculoDTO);
        return "redirect:/vehiculo/" + vehiculoDTO.getId();
    }


    // borrar
    @GetMapping("/{id}/delete")
    public String eliminarVehiculo(Model model, @PathVariable Long id){

        restClient.delete()
            .uri("/vehiculos/{id}/delete", id)
            .retrieve()
            .body(VehiculoDTO.class);

        //boolean resultado = vehiculoService.eliminarVehiculo(vehiculoService.mostrarVehiculo(id));
        //model.addAttribute("resultado", resultado);
        return "redirect:/";
    }

}
