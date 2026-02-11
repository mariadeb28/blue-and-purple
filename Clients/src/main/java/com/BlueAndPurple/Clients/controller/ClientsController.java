package com.BlueAndPurple.Clients.controller;

import com.BlueAndPurple.Clients.model.Clients;
import com.BlueAndPurple.Clients.service.ClientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("clients")
public class ClientsController {
    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @PostMapping
    public ResponseEntity<Clients> saveClients(@RequestBody Clients client){
        clientsService.saveClients(client);
        System.out.println(">>>> Recebido no Controller: " + client);
        return ResponseEntity.ok(client);

    }

    @GetMapping("{codigo}")
    public ResponseEntity<Clients> getByCode(@PathVariable("codigo")Long codigo){
        return clientsService
                .getByCode(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<Void> delete(@PathVariable("codigo") Long codigo){
        var client = clientsService.getByCode(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "client don't exist"
                ));
        clientsService.delete(client);
        return ResponseEntity.noContent().build();
    }


}
