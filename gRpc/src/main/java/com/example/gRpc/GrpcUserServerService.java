package com.example.gRpc;

import net.devh.examples.grpc.lib.UserGrpc;
import net.devh.examples.grpc.lib.UserInfo;
import net.devh.examples.grpc.lib.UserInfoList;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

@GrpcService(UserGrpc.class)
public class GrpcUserServerService extends UserGrpc.UserImplBase {
	@Override
    public void add(net.devh.examples.grpc.lib.UserInfo request,
    		io.grpc.stub.StreamObserver<net.devh.examples.grpc.lib.UserInfoList> responseObserver) {
		
		
    }

	@Override
    public void remove(net.devh.examples.grpc.lib.UserInfo request,
    		io.grpc.stub.StreamObserver<net.devh.examples.grpc.lib.UserInfoList> responseObserver) {
    }

	@Override
    public void update(net.devh.examples.grpc.lib.UserInfo request,
    		io.grpc.stub.StreamObserver<net.devh.examples.grpc.lib.UserInfoList> responseObserver) {
		
    }

	@Override
    public void find(net.devh.examples.grpc.lib.UserInfo request,
    		io.grpc.stub.StreamObserver<net.devh.examples.grpc.lib.UserInfoList> responseObserver) {
		UserInfo userInfo = UserInfo.newBuilder().setName("qing").build();
		UserInfoList userInfoList = UserInfoList.newBuilder().addUserInfo(userInfo).build();
		responseObserver.onNext(userInfoList);
	    responseObserver.onCompleted();
    }

}
