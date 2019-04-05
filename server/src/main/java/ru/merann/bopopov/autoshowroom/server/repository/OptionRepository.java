package ru.merann.bopopov.autoshowroom.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.merann.bopopov.autoshowroom.server.model.Option;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findOneByName(String name);

    @Query("from Option o where o.name in :names" )
    List<Option> findAllByNames(@Param("names") List<String> names);

    @Query("from Option o where lower(o.name) like lower(concat(?1,'%'))")
    List<Option> findAllByName(String name);
}
