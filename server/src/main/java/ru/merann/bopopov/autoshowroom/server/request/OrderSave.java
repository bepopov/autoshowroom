package ru.merann.bopopov.autoshowroom.server.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderSave {

    private Long userId;

    private Long modelId;

    private List<Long> options;

}
