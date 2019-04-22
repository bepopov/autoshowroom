package ru.merann.bopopov.autoshowroom.soapclient.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.server.ws.*;
import ru.merann.bopopov.autoshowroom.soapclient.config.CommandPatterns;
import ru.merann.bopopov.autoshowroom.soapclient.valueproviders.*;
import ru.merann.bopopov.autoshowroom.soapclient.config.TableConfig;
import ru.merann.bopopov.autoshowroom.soapclient.service.ConnectionService;
import ru.merann.bopopov.autoshowroom.soapclient.service.ConsoleService;
import ru.merann.bopopov.autoshowroom.soapclient.service.OrderService;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@ShellComponent
public class OrderServiceImpl implements OrderService {

    private Set<Long> orders;
    private ConnectionService connectionService;
    private ConsoleService consoleService;
    private final OrderWebService webService;
    private final Pattern idPattern = CommandPatterns.getIdPattern();
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(ConnectionService connectionService, ConsoleService consoleService) {
        this.connectionService = connectionService;
        this.consoleService = consoleService;
        this.orders = new HashSet<>();
        ru.merann.bopopov.autoshowroom.server.ws.impl.OrderService orderService =
                new ru.merann.bopopov.autoshowroom.server.ws.impl.OrderService();
        this.webService = orderService.getOrderServicePort();
    }

    @Override
    @ShellMethod("Create order. Syntax: --order \"<make> <model> <options separated by AND>\". Make and model are required params.")
    public void createOrder(@ShellOption(value = "--order", valueProvider = OrderValueProvider.class) OrderRequest orderRequest) {
        Order order = webService.save(connectionService.getClientId(), orderRequest);
        consoleService.write("New order was created: %s.", order.toString());
    }

    @Override
    @ShellMethod("Edit order. Syntax: edit-order --make <make> --model <model> --options <options separated by AND>")
    public void editOrder(@ShellOption(defaultValue = "", valueProvider = OrderIdValueProvider.class) String order,
                          @ShellOption(defaultValue = "", valueProvider = MakeValueProvider.class) String make,
                          @ShellOption(defaultValue = "", valueProvider = ModelValueProvider.class) String model,
                          @ShellOption(defaultValue = "", valueProvider = OptionValueProvider.class) List<Long> options) {
        OrderRequest orderRequest = new OrderRequest();
        //edit-order --order=10 --options="Зимняя резина"
        //edit-order --order 10 --options "Зимняя резина"
        OrderRequestCar car = new OrderRequestCar();
        if (!make.equals("")) {
            if (!model.equals("")) {
                car.setMake(getId(make));
                car.setModel(getId(model));
            }
            else {
                throw new IllegalArgumentException("Expected param --model is not found");
            }
        }
        orderRequest.setCar(car);
        orderRequest.withOptions(new ArrayList<>());
        if (options != null) {
            orderRequest.withOptions(options);
        }
        if (!order.equals("")) {
            Long orderId = Long.valueOf(order);
            Order updatedOrder = webService.change(connectionService.getClientId(), orderId, orderRequest);
            if (updatedOrder == null) {
                LOGGER.log(Level.INFO, "Order not changed. Order value received: null");
            }
            else {
                LOGGER.log(Level.INFO, String.format("Order changed: %s", updatedOrder.toString()));
                consoleService.write("Order changed");
            }
        }
    }

    @Override
    @ShellMethod("Delete order. Syntax: delete-order --order <id>")
    public void deleteOrder(@ShellOption(value = "--order", valueProvider = OrderIdValueProvider.class) Long id) {
        webService.delete(id);
        LOGGER.log(Level.INFO, String.format("Order #%s was deleted.", id.toString()));
        consoleService.write("Order #%s was deleted.", id.toString());
    }

    @Override
    @ShellMethod("Get order list. Syntax: get-orders.")
    public Table getOrders() {
        LOGGER.log(Level.INFO, "Order list requested");
        List<Order> orders = webService.getAll();
        LOGGER.log(Level.INFO, String.format("Received orders: %s", orders.toString()));
        return TableConfig.getTable(orders);
    }

    @Override
    @ShellMethod("Get order list by status. Syntax: get-orders --status <status>.")
    public Table getOrdersByStatus(@ShellOption(valueProvider = StatusValueProvider.class) Status status) {
        LOGGER.log(Level.INFO, String.format("Order list with %s status is requested", status));
        List<Order> orders = webService.getAllByClientIdAndStatus(connectionService.getClientId(), status);
        LOGGER.log(Level.INFO, String.format("Received orders: %s", orders.toString()));
        return TableConfig.getTable(orders);
    }

    private Long getId(String keyValue) {
        Matcher optionMatcher = idPattern.matcher(keyValue);
        if (optionMatcher.find()) {
            return Long.valueOf(optionMatcher.group("id"));
        }
        else {
            throw new IllegalArgumentException("Value should contain id. Example: Audi(#1)");
        }
    }
}
