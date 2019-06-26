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
    
    public String findById(UserInfo user) {
        UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(serverChannel);
        UserInfoList response = stub.findById(user);
        String jsonFormat = JsonFormat.printToString(response);
        return jsonFormat;
    }
    
    public String add(UserInfo user) {
    	UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(serverChannel);
    	UserInfoList response = stub.add(user);
    	String jsonFormat = JsonFormat.printToString(response);
    	return jsonFormat;
    }
    
    public String update(UserInfo user) {
        UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(serverChannel);
        UserInfoList response = stub.update(user);
        String jsonFormat = JsonFormat.printToString(response);
        return jsonFormat;
    }
    
    public String del(int id) {
        UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(serverChannel);
        UserInfoList response = stub.remove(UserInfo.newBuilder().setId(id).build());
        String jsonFormat = JsonFormat.printToString(response);
        return jsonFormat;
    }
    
}
