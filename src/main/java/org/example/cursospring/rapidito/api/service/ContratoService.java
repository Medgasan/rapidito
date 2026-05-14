package org.example.cursospring.rapidito.api.service;

import org.example.cursospring.rapidito.api.dto.ContratoDTO;
import org.example.cursospring.rapidito.api.entity.Contrato;
import org.example.cursospring.rapidito.api.mappers.ContratoMapper;
import org.example.cursospring.rapidito.api.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContratoService implements IContratoService {

    private final ContratoRepository contratoRepository;
    private final ContratoMapper contratoMapper;


    public ContratoService(ContratoRepository contratoRepository, ContratoMapper contratoMapper) {
        this.contratoRepository = contratoRepository;
        this.contratoMapper = contratoMapper;
    }


    @Override
    public List<ContratoDTO> mostrarContratos(){
        List<Contrato> contratos = this.contratoRepository.findAll();
        return contratoMapper.toContratoDTOList(contratos);
    }


    @Override
    public ContratoDTO crearContrato(ContratoDTO contratoDTO) {
        Contrato contrato = contratoMapper.toContrato(contratoDTO);
        contrato = contratoRepository.save(contrato);
        return contratoMapper.toContratoDTO(contrato);
    }


    @Override
    public ContratoDTO mostrarContrato(Long id) {
        Contrato contrato = contratoRepository.getReferenceById(id);
        return contratoMapper.toContratoDTO(contrato);
    }

    @Override
    public ContratoDTO actualizarContrato(ContratoDTO contratoDTO) {
        return crearContrato(contratoDTO);
    }

    @Override
    public boolean eliminarContrato(ContratoDTO contratoDTO) {
        Contrato contrato = contratoMapper.toContrato(contratoDTO);
        Long id = contrato.getId();
        contratoRepository.delete(contrato);
        return contratoRepository.findById(id).isEmpty();
    }
}
