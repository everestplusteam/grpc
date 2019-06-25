package com.example.gRpcclient;

import org.springframework.stereotype.Service;

import com.googlecode.protobuf.format.JsonFormat;

import io.grpc.Channel;
import net.devh.examples.grpc.lib.UserGrpc;
import net.devh.examples.grpc.lib.UserInfo;
import net.devh.examples.grpc.lib.UserInfoList;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;

@Service
public class GrpcUserClientService {

	@GrpcClient("local-grpc-server")
    private Channel serverChannel;

    public String find() {
        UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(serverChannel);
        UserInfoList response = stub.find(UserInfo.newBuilder().setName("").build());
        String jsonFormat = JsonFormat.printToString(response);
        return jsonFormat;
    }
}
