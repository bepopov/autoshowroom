package ru.merann.bopopov.autoshowroom.restclient.config;

import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModelBuilder;
import ru.merann.bopopov.autoshowroom.restclient.model.Option;
import ru.merann.bopopov.autoshowroom.restclient.model.Order;

import java.util.ArrayList;
import java.util.List;

public class TableConfig {

    private static final List<String> orderHeaders;

    static {
        orderHeaders = new ArrayList<>();
        initializeOrderHeaders();
    }

    private static void initializeOrderHeaders() {
        orderHeaders.add("ID");
        orderHeaders.add("Make");
        orderHeaders.add("Model");
        orderHeaders.add("Car Price");
        orderHeaders.add("Status");
        orderHeaders.add("Client");
        orderHeaders.add("Options");
        orderHeaders.add("Total Price");
    }

    public static Table getTable(List<Order> orders) {
        TableModelBuilder<Object> tableModelBuilder = new TableModelBuilder<>();
        tableModelBuilder = tableModelBuilder.addRow();
        if (orders.size() == 0) {
            tableModelBuilder.addValue("No order");
            return new TableBuilder(tableModelBuilder.build()).addFullBorder(BorderStyle.fancy_light).build();
        }
        for (String header : orderHeaders) {
            tableModelBuilder.addValue(header);
        }
        for (Order order : orders) {
            tableModelBuilder = tableModelBuilder.addRow();
            tableModelBuilder.addValue(order.getId());
            tableModelBuilder.addValue(order.getCar().getModel().getMake().getName());
            tableModelBuilder.addValue(order.getCar().getModel().getName());
            tableModelBuilder.addValue(order.getCar().getModel().getPrice());
            tableModelBuilder.addValue(order.getStatus().getValue());
            tableModelBuilder.addValue(order.getClient().getName());
            List<Option> options = order.getCar().getOptions();
            if (options.size() == 0) {
                tableModelBuilder.addValue("");
                tableModelBuilder.addValue(order.getCar().getModel().getPrice());
            }
            else {
                StringBuilder optionString = new StringBuilder();
                Long totalPrice = order.getCar().getModel().getPrice();
                for (Option option : options) {
                    optionString
                            .append(option.getName())
                            .append(" [Price: ")
                            .append(option.getPrice())
                            .append("],\n");
                    totalPrice += option.getPrice();
                }
                optionString.delete(optionString.length() - 2, optionString.length());
                tableModelBuilder.addValue(optionString);
                tableModelBuilder.addValue(totalPrice);
            }
        }
        TableBuilder tableBuilder = new TableBuilder(tableModelBuilder.build());
        return tableBuilder.addFullBorder(BorderStyle.fancy_light).build();
    }
}
