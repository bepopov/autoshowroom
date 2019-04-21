package ru.merann.bopopov.autoshowroom.restclient.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.Table;
import org.springframework.stereotype.Service;
import ru.merann.bopopov.autoshowroom.restclient.service.*;
import ru.merann.bopopov.autoshowroom.restclient.config.*;
import ru.merann.bopopov.autoshowroom.restclient.valueproviders.*;
import ru.merann.bopopov.autoshowroom.restclient.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@ShellComponent
public class OrderServiceImpl implements OrderService {

    private Set<Long> orders;
    private ConnectionService connectionService;
    private ConsoleService consoleService;
    private final WebClientService webService;
    private final Pattern idPattern = CommandPatterns.getIdPattern();
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(ConnectionService connectionService,
                            ConsoleService consoleService, WebClientService webService) {
        this.connectionService = connectionService;
        this.consoleService = consoleService;
        this.orders = new HashSet<>();
        this.webService = webService;
    }

    @Override
    @ShellMethod("Create order. Syntax: --order \"<make> <model> <options separated by AND>\". Make and model are required params.")
    public void createOrder(@ShellOption(value = "--order", valueProvider = OrderValueProvider.class) OrderRequest orderRequest) {
        Order order = webService.createOrder(orderRequest, connectionService.getClientId());
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
        orderRequest.setOptions(new ArrayList<>());
        if (options != null) {
            orderRequest.setOptions(options);
        }
        if (!order.equals("")) {
            Long orderId = Long.valueOf(order);
            Order updatedOrder = webService.editOrder(orderRequest, connectionService.getClientId(), orderId);
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
        webService.deleteOrder(id, connectionService.getClientId());
        LOGGER.log(Level.INFO, String.format("Order #%s was deleted.", id.toString()));
        consoleService.write("Order #%s was deleted.", id.toString());
    }

    @Override
    @ShellMethod("Get order list. Syntax: get-orders.")
    public Table getOrders() {
        LOGGER.log(Level.INFO, "Order list requested");
        ResultListOrder orders = webService.getOrders();
        LOGGER.log(Level.INFO, String.format("Received orders: %s", orders.toString()));
        return TableConfig.getTable(orders.getItems());
    }

    @Override
    @ShellMethod("Get order list by status. Syntax: get-orders --status <status>.")
    public Table getOrdersByStatus(@ShellOption(valueProvider = StatusValueProvider.class) Status status) {
        LOGGER.log(Level.INFO, String.format("Order list with %s status is requested", status));
        ResultListOrder orders = webService.getOrdersByStatus(status, connectionService.getClientId());
        LOGGER.log(Level.INFO, String.format("Received orders: %s", orders.toString()));
        return TableConfig.getTable(orders.getItems());
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
