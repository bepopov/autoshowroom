package ru.merann.bopopov.autoshowroom.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.merann.bopopov.autoshowroom.server.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);

}
