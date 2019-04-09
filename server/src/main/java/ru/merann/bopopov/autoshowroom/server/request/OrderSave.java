package ru.merann.bopopov.autoshowroom.server.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderSave {

    private String username;

    private String model;

    private List<String> options;

}
