package ru.merann.bopopov.autoshowroom.server.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderChange {

    Long orderId;

    Long modelId;

    List<Long> options;

    Long userId;
}
