package ru.merann.bopopov.autoshowroom.groovy_server.service.impl

import com.datastax.driver.core.utils.UUIDs
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.merann.bopopov.autoshowroom.groovy_server.model.Car
import ru.merann.bopopov.autoshowroom.groovy_server.model.Client
import ru.merann.bopopov.autoshowroom.groovy_server.model.Make
import ru.merann.bopopov.autoshowroom.groovy_server.model.Model
import ru.merann.bopopov.autoshowroom.groovy_server.model.Option
import ru.merann.bopopov.autoshowroom.groovy_server.model.Order
import ru.merann.bopopov.autoshowroom.groovy_server.model.OrderRequest
import ru.merann.bopopov.autoshowroom.groovy_server.model.Status
import ru.merann.bopopov.autoshowroom.groovy_server.repository.ClientRepository
import ru.merann.bopopov.autoshowroom.groovy_server.repository.MakeRepository
import ru.merann.bopopov.autoshowroom.groovy_server.repository.ModelRepository
import ru.merann.bopopov.autoshowroom.groovy_server.repository.OptionRepository
import ru.merann.bopopov.autoshowroom.groovy_server.repository.OrderRepository
import ru.merann.bopopov.autoshowroom.groovy_server.service.OrderService

@Service
class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository
    OptionRepository optionRepository
    MakeRepository makeRepository
    ModelRepository modelRepository
    ClientRepository clientRepository

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository, OptionRepository optionRepository, MakeRepository makeRepository, ModelRepository modelRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository
        this.optionRepository = optionRepository
        this.makeRepository = makeRepository
        this.modelRepository = modelRepository
        this.clientRepository = clientRepository
    }

    @Override
    void save(OrderRequest orderRequest, Long userId) {
        List<Option> options = optionRepository.findAllById(orderRequest.getOptions()).asList()
        Model model = modelRepository.findById(orderRequest.getCar().getModel()).get()
        Make make = makeRepository.findById(orderRequest.getCar().getMake()).get()
        Client client = clientRepository.findById(userId).get()
        Order order = new Order()
        Car car = new Car()
        car.setModel(model.getName())
        car.setMake(make.getName())
        car.setOptions(options.stream().map({e -> e.name}).collect().asList())
        order.setCar(car)
        order.setClient(client.getName())
        order.setStatus(Status.ACCEPTED)
        order.setId(UUID.randomUUID().toString())
        orderRepository.save(order)
    }
}
