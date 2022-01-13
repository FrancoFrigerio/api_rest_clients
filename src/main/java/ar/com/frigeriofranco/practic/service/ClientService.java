package ar.com.frigeriofranco.practic.service;


import ar.com.frigeriofranco.practic.dto.*;
import ar.com.frigeriofranco.practic.model.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {

    public ClientResponseDto findById(Long id);

    public List<ClientListRespDto> findAllClients();

    public ClientResponseDto saveClient(ClientRequestDto clientRequestDto);

    Client getById(Long id);

    Map<String,Object> deleteById(Long id);

    List<ClientMetricsDto> getMetrics();

    Map<String,List<ClientMetricsDto>>getAllMetrics();

    Map<String,List<MetricsResponseDto>>getMetricsByDate();
}
