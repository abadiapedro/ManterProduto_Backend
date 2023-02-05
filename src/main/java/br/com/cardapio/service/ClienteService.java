package br.com.cardapio.service;

import br.com.cardapio.dto.ClienteDto;
import br.com.cardapio.model.Cliente;
import br.com.cardapio.model.TipoCliente;
import br.com.cardapio.repository.ClienteRepository;
import br.com.cardapio.repository.TipoClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ClienteDto> getAllClientes(Pageable paginacao){
        return clienteRepository.findAll(paginacao)
                .map(cliente -> modelMapper.map(cliente, ClienteDto.class));
    }
    public ClienteDto getClienteById(Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cliente não encontrado")
        );

        return modelMapper.map(cliente, ClienteDto.class);
    }

    public ClienteDto criarCliente(ClienteDto clienteDto) throws Exception {
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);

        if(clienteDto.getTipoCliente() != null && cliente.getTipoCliente().getId() != null){
            TipoCliente tipoCliente = tipoClienteRepository.findById(clienteDto.getTipoCliente().getId()).orElseThrow(
                    () -> new EntityNotFoundException("Tipo Cliente não encontrado"));

            cliente.setTipoCliente(tipoCliente);

        }

        cliente = clienteRepository.save(cliente);

        return modelMapper.map(cliente, ClienteDto.class);
    }

    public ClienteDto updateCliente(Long id, ClienteDto clienteDto) throws Exception {
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
        cliente.setId(id);

        if(clienteDto.getTipoCliente() != null && cliente.getTipoCliente().getId() != null){
            TipoCliente tipoCliente = tipoClienteRepository.findById(clienteDto.getTipoCliente().getId()).orElseThrow(
                    () -> new EntityNotFoundException("Tipo Cliente não encontrado"));

            cliente.setTipoCliente(tipoCliente);
        }
        cliente = clienteRepository.save(cliente);

        return modelMapper.map(cliente, ClienteDto.class);
    }

    public void excluirCliente(Long id){
        clienteRepository.deleteById(id);
    }
}
