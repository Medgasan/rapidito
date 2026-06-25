package org.example.cursospring.rapidito.api.factory;

import org.example.cursospring.rapidito.api.entity.Contrato;
import org.example.cursospring.rapidito.api.entity.ContratoSuplemento;
import org.example.cursospring.rapidito.api.entity.Reserva;
import org.example.cursospring.rapidito.api.entity.Vehiculo;
import org.example.cursospring.rapidito.api.entity.embedded.AuditoriaAgentes;
import org.springframework.stereotype.Component;


@Component
public class ContratoFactory {

    public Contrato crearDesdeReserva(Reserva reserva, Vehiculo vehiculoAsignado, Long agenteId) {
        Contrato contrato = new Contrato();
        contrato.setReservaOriginal(reserva);
        contrato.setCliente(reserva.getCliente());
        contrato.setVehiculo(vehiculoAsignado); // Aquí ya asignas la matrícula real
        contrato.setFechaInicio(reserva.getFechaInicio());
        contrato.setFechaFin(reserva.getFechaFin());
        contrato.setEstado(Contrato.EstadoContrato.ACTIVO);

        contrato.setPrecioVehiculo(reserva.getVehiculo().getPrecioDia());

        // CLONACIÓN Y AISLAMIENTO DE SUPLEMENTOS (Garantizando Bidireccionalidad)
        if (reserva.getSuplemento() != null) {
            reserva.getSuplemento().forEach(reservaSup -> {
                ContratoSuplemento conSup = new ContratoSuplemento();

                conSup.setSuplemento(reservaSup.getSuplemento());
                conSup.setNombreAplicado(reservaSup.getNombreAplicado());
                conSup.setPrecioBaseAplicado(reservaSup.getPrecioBaseAplicado());
                conSup.setTotalCalculado(reservaSup.getTotalCalculado());
                conSup.setUnidadesCalculadas(reservaSup.getUnidadesCalculadas());

                // Trazabilidad del agente de mostrador
                AuditoriaAgentes auditoriaAgentes = new AuditoriaAgentes();
                auditoriaAgentes.setIdAgenteCreacion(agenteId);
                conSup.setTrazabilidad(auditoriaAgentes);

                contrato.addSuplemento(conSup);
            });
        }

        contrato.calcularTotalContrato();

        return contrato;
    }

}
