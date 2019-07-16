package ru.merann.bopopov.autoshowroom.groovy_server.service.impl

import ru.merann.bopopov.autoshowroom.groovy_server.model.Car
import ru.merann.bopopov.autoshowroom.groovy_server.model.Model
import ru.merann.bopopov.autoshowroom.groovy_server.model.Option
import ru.merann.bopopov.autoshowroom.groovy_server.model.Order
import ru.merann.bopopov.autoshowroom.groovy_server.model.OrderRequest
import ru.merann.bopopov.autoshowroom.groovy_server.model.Status
import ru.merann.bopopov.autoshowroom.groovy_server.repository.MakeRepository
import ru.merann.bopopov.autoshowroom.groovy_server.repository.ModelRepository
import ru.merann.bopopov.autoshowroom.groovy_server.repository.OptionRepository
import ru.merann.bopopov.autoshowroom.groovy_server.repository.OrderRepository
import ru.merann.bopopov.autoshowroom.groovy_server.service.OrderService

class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository
    OptionRepository optionRepository
    MakeRepository makeRepository
    ModelRepository modelRepository

    @Override
    void save(OrderRequest orderRequest) {
        List<Option> options = optionRepository.findAllById(orderRequest.getOptions()).asList()
        Model model = modelRepository.findById(orderRequest.getCar().getModel()).get()
        Order order = new Order()
        Car car = new Car()
        car.setModel(model)
        car.setOptions(options)
        order.setCar(car)
        order.setStatus(Status.ACCEPTED)
        orderRepository.save(order)
    }
}
