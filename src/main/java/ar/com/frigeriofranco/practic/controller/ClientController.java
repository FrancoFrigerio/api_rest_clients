package ar.com.frigeriofranco.practic.controller;


import ar.com.frigeriofranco.practic.dto.ClientRequestDto;
import ar.com.frigeriofranco.practic.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    ClientService clientService;



    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(clientService.findById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?>deleteClient(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(clientService.deleteById(id));
    }


    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?>findAll(){
        return ResponseEntity.ok().body(clientService.findAllClients());
    }


    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?>saveClient(@Valid @RequestBody ClientRequestDto clientRequestDto){
        return new ResponseEntity<>(clientService.saveClient(clientRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/clientsMetrics")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?>getMetrics(){
        return new ResponseEntity<>(clientService.getMetrics(),HttpStatus.OK);
    }

}
