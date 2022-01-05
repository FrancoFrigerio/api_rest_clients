package ar.com.frigeriofranco.practic.service.impl;


import ar.com.frigeriofranco.practic.dto.ClientListRespDto;
import ar.com.frigeriofranco.practic.dto.ClientMetricsDto;
import ar.com.frigeriofranco.practic.dto.ClientRequestDto;
import ar.com.frigeriofranco.practic.dto.ClientResponseDto;
import ar.com.frigeriofranco.practic.exceptions.ElementNotFound;
import ar.com.frigeriofranco.practic.model.Client;
import ar.com.frigeriofranco.practic.repository.ClientRepository;
import ar.com.frigeriofranco.practic.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper mapper;


    @Value("error.item.provided.not.found")
    private String elementNotFound;

    @Value("message.successfully.item.removed")
    private String elementSuccessfullyDeleted;

    @Autowired
    private MessageSource messageSource;

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
        if(clientRepository.findById(id).isEmpty()){
            throw new NoSuchElementException(messageSource.getMessage(elementNotFound,null,Locale.getDefault()));
        }
        clientRepository.deleteById(id);
        Map<String,Object> body = new HashMap<>();
        body.put("message",messageSource.getMessage(elementSuccessfullyDeleted,null,Locale.getDefault() ));
        body.put("date", new Date());
        return body;
    }

    @Override
    public List<ClientMetricsDto> getMetrics() {
        log.info("esta vacio?" + clientRepository.getMetrics().isEmpty());
        List<ClientMetricsDto> listClientsMetrics = new ArrayList<>();
        clientRepository.getMetrics().forEach(element ->{
            log.info("total " + mapper.map(element,ClientMetricsDto.class));
            ClientMetricsDto clientMetricsDto = mapper.map(element,ClientMetricsDto.class);
            clientMetricsDto.setTotal(clientRepository.getTotal(clientMetricsDto.getId()));
            listClientsMetrics.add(clientMetricsDto);
        });
        return listClientsMetrics;
    }
}
