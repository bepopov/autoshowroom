package ru.merann.bopopov.autoshowroom.server.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {

    @ManyToOne
    @JoinColumn(name = "model_id")
    Model model;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_options",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id")})
    List<Option> options;

}
