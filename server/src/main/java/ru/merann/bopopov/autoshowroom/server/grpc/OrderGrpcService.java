package ru.merann.bopopov.autoshowroom.server.grpc;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import ru.merann.bopopov.autoshowroom.grpc.OrderRequestOuterClass;
import ru.merann.bopopov.autoshowroom.grpc.OrderServiceGrpc;
import ru.merann.bopopov.autoshowroom.server.model.Order;
import ru.merann.bopopov.autoshowroom.server.model.OrderRequest;
import ru.merann.bopopov.autoshowroom.server.model.OrderRequestCar;
import ru.merann.bopopov.autoshowroom.server.service.OrderService;

@GRpcService
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    @Autowired
    private OrderService orderService;

    @Override
    public void save(OrderRequestOuterClass.OrderRequest request, StreamObserver<OrderRequestOuterClass.OrderResponse> responseObserver) {
        try {
            OrderRequest orderRequest = new OrderRequest();
            OrderRequestCar requestCar = new OrderRequestCar();
            requestCar.setMake(request.getCar().getMake());
            requestCar.setModel(request.getCar().getModel());
            orderRequest.setCar(requestCar);
            orderRequest.setOptions(request.getOptionList());
            Order order = orderService.save(request.getClient(), orderRequest);
            responseObserver.onNext(OrderRequestOuterClass.OrderResponse.newBuilder()
                    .setId(order.getId())
                    .setStatus(OrderRequestOuterClass.OrderResponse.SaveStatus.SUCCESS)
                    .build());
        }
        catch (Exception e) {
            responseObserver.onNext(OrderRequestOuterClass.OrderResponse.newBuilder()
                    .setStatus(OrderRequestOuterClass.OrderResponse.SaveStatus.FAIL)
                    .build());
        }
        responseObserver.onCompleted();
    }
}
