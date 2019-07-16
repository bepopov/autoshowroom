package ru.merann.bopopov.autoshowroom.groovy_server.service.impl

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

    @Override
    void save(OrderRequest orderRequest, Long userId) {
        List<Option> options = optionRepository.findAllById(orderRequest.getOptions()).asList()
        Model model = modelRepository.findById(orderRequest.getCar().getModel()).get()
        Make make = makeRepository.findById(orderRequest.getCar().getMake()).get()
        Client client = clientRepository.findById(userId).get()
        Order order = new Order()
        Car car = new Car()
        car.setModel(model.getName())
        car.setMake(make.get)
        car.setOptions(options.stream().map({e -> e.name}).collect().asList())
        order.setCar(car)
        order.setClient(client.getName())
        order.setStatus(Status.ACCEPTED)
        orderRepository.save(order)
    }
}
