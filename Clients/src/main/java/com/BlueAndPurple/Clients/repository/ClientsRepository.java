package com.BlueAndPurple.Clients.repository;

import com.BlueAndPurple.Clients.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
}
