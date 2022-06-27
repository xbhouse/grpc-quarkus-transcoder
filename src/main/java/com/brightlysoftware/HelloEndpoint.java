package com.brightlysoftware;

import com.brightlysoftware.HelloGrpc;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloEndpoint {
    @GrpcClient("hello")
    HelloGrpc helloService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/{name}")
    public Uni<String> hello(String name) {
        return helloService.sayHello(HelloRequest.newBuilder().setName(name).build())
                .onItem().transform(helloReply -> helloReply.getMessage());
    }
}
