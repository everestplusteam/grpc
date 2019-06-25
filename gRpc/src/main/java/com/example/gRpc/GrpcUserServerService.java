package com.example.gRpc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.sql.DatabaseConnection;

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
		DatabaseConnection sqlUtil = new DatabaseConnection();
		UserInfoList.Builder userInfoList = UserInfoList.newBuilder();
		try {
			Connection conn = sqlUtil.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select * from userinfo";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int id  = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                UserInfo.Builder userInfo = UserInfo.newBuilder();
                userInfo.setId(id);
                userInfo.setAge(age);
                userInfo.setName(name);
                userInfoList.addUserInfo(userInfo);
	            }
	            rs.close();
	            stmt.close();
	            conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlUtil.closeConn();
		}
		responseObserver.onNext(userInfoList.build());
	    responseObserver.onCompleted();
    }

}
