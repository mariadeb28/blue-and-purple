package com.BlueAndPurple.Clients.service;

import com.BlueAndPurple.Clients.model.Clients;
import com.BlueAndPurple.Clients.repository.ClientsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientsService {
    private final ClientsRepository clientsRepository;

    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public Clients saveClients(Clients client){
        client.setAtivo(true);
        return clientsRepository.save(client);
    }

    public Optional<Clients> getByCode(Long client){
        return clientsRepository.findById(client);
    }

    public void delete(Clients client) {
        client.setAtivo(false);
        clientsRepository.save(client);
    }
}
