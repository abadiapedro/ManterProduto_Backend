package br.com.cardapio.service;

import br.com.cardapio.dto.TipoClienteDto;
import br.com.cardapio.model.TipoCliente;
import br.com.cardapio.repository.TipoClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TipoClienteService {

    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<TipoClienteDto> getAllTiposClientes (Pageable paginacao){
        return tipoClienteRepository.findAll(paginacao)
                .map(tipoCliente -> modelMapper.map(tipoCliente, TipoClienteDto.class));
    }
    public TipoClienteDto getTiposClientesById(Long id){
        TipoCliente tipoCliente = tipoClienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Tipo Cliente não encontrado")
        );

        return modelMapper.map(tipoCliente, TipoClienteDto.class);
    }

    public TipoClienteDto criarTipoCliente(TipoClienteDto tipoClienteDto){
        TipoCliente tipoCliente = modelMapper.map(tipoClienteDto, TipoCliente.class);
        tipoCliente = tipoClienteRepository.save(tipoCliente);

        return modelMapper.map(tipoCliente, TipoClienteDto.class);
    }

    public TipoClienteDto updateTipoCliente(Long id, TipoClienteDto tipoClienteDto){
        TipoCliente tipoCliente = modelMapper.map(tipoClienteDto, TipoCliente.class);
        tipoCliente.setId(id);
        tipoCliente = tipoClienteRepository.save(tipoCliente);

        return modelMapper.map(tipoCliente, TipoClienteDto.class);
    }

    public void excluirTipoCliente(Long id){
        tipoClienteRepository.deleteById(id);
    }
}
