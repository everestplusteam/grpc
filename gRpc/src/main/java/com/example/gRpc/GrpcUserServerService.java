package com.example.gRpc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection();			
			String sqlInset = "insert into userinfo(id, name, age) values(?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sqlInset);	
			stmt.setInt(1, request.getId());         
			stmt.setString(2, request.getName());    
			stmt.setInt(3, request.getAge());  
			stmt.executeUpdate();
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    try {
		        conn.close(); 
		    } catch(SQLException e) {
		        e.printStackTrace();
		    }
		}
		find(request,responseObserver);
		
    }

	@Override
    public void remove(net.devh.examples.grpc.lib.UserInfo request, io.grpc.stub.StreamObserver<net.devh.examples.grpc.lib.UserInfoList> responseObserver) {
        PreparedStatement st = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "delete from userinfo where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, request.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseConnection.closeConn();
        }
        find(request, responseObserver);
    }

    @Override
    public void update(net.devh.examples.grpc.lib.UserInfo request, io.grpc.stub.StreamObserver<net.devh.examples.grpc.lib.UserInfoList> responseObserver) {
        PreparedStatement st = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "update userinfo set name=?,age=? where id=?";
            st = conn.prepareStatement(sql);

            st.setString(1, request.getName());
            st.setInt(2, request.getAge());
            st.setInt(3, request.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseConnection.closeConn();
        }
        find(request, responseObserver);
    }

	
	

	@Override
    public void find(net.devh.examples.grpc.lib.UserInfo request,
    		io.grpc.stub.StreamObserver<net.devh.examples.grpc.lib.UserInfoList> responseObserver) {
		UserInfoList.Builder userInfoList = UserInfoList.newBuilder();
		try {
			Connection conn = DatabaseConnection.getConnection();
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
			DatabaseConnection.closeConn();
		}
		responseObserver.onNext(userInfoList.build());
	    responseObserver.onCompleted();
    }

}
