package com.example.gRpcclient;

import org.springframework.stereotype.Service;

import com.googlecode.protobuf.format.JsonFormat;
import com.reuters.proto.lib.UserGrpc;
import com.reuters.proto.lib.UserInfo;
import com.reuters.proto.lib.UserInfoList;

import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;

@Service
public class GrpcUserClientService {

	@GrpcClient("local-grpc-server")
    private Channel serverChannel;

    public String findFromMem() {
        UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(serverChannel);
        UserInfoList response = stub.findFromMem(UserInfo.newBuilder().setName("").build());
        String jsonFormat = JsonFormat.printToString(response);
        return jsonFormat;
    }
    
   
}
