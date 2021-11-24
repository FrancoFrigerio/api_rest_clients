package ar.com.frigeriofranco.practic.service.impl;


import ar.com.frigeriofranco.practic.dto.ClientListRespDto;
import ar.com.frigeriofranco.practic.dto.ClientRequestDto;
import ar.com.frigeriofranco.practic.dto.ClientResponseDto;
import ar.com.frigeriofranco.practic.model.Client;
import ar.com.frigeriofranco.practic.repository.ClientRepository;
import ar.com.frigeriofranco.practic.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ClientResponseDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow();
        Double saldo = clientRepository.sumBills(id);
        ClientResponseDto clientResponse = mapper.map(client,ClientResponseDto.class);
            clientResponse.setSaldo(saldo);

        return clientResponse;
    }

    @Override
    public List<ClientListRespDto> findAllClients() {
        List<ClientListRespDto> clientsDto = new ArrayList<>();
        clientRepository.findAll().forEach(client -> {
            clientsDto.add(mapper.map(client, ClientListRespDto.class));
        });
        return clientsDto;
    }

    @Override
    public ClientResponseDto saveClient(ClientRequestDto clientRequestDto) {
        Client clientToSave = mapper.map(clientRequestDto,Client.class);
        return mapper.map(clientRepository.save(clientToSave),ClientResponseDto.class);
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public Map<String, Object> deleteById(Long id) {
        clientRepository.deleteById(id);
        Map<String,Object> body = new HashMap<>();
        body.put("message","Element was successfully deleted");
        body.put("date", new Date());
        return body;
    }
}
