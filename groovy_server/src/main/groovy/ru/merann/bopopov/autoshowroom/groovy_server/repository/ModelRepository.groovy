package ru.merann.bopopov.autoshowroom.groovy_server.repository

import org.springframework.data.repository.CrudRepository
import ru.merann.bopopov.autoshowroom.groovy_server.model.Model

interface ModelRepository extends CrudRepository<Model, Long> {

}