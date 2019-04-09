package ru.merann.bopopov.autoshowroom.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.merann.bopopov.autoshowroom.server.model.Make;

import java.util.List;

public interface MakeRepository extends JpaRepository<Make, Long> {

    @Query("from Make m where lower(m.name) like lower(concat(?1,'%'))")
    List<Make> findAllNameByName(String name);

}
