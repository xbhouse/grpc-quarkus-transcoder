package com.transcoder;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

import javax.transaction.Transactional;

@GrpcService
public class HelloGrpcService implements HelloGrpc {

    @Transactional
    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return Uni.createFrom().item("Hello " + request.getName() + "!")
                .map(msg -> HelloReply.newBuilder().setMessage(msg).build());
    }

}
