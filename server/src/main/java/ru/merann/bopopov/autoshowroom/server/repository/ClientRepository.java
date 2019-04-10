package ru.merann.bopopov.autoshowroom.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.merann.bopopov.autoshowroom.server.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c.id from Client c where c.name=?1")
    Long findByName(String name);

    Client findOneById(Long id);

}
