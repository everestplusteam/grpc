package com.reuters.grpcclient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.reuters.grpcclient.bean.User;
import com.reuters.grpcclient.service.inf.UserService;
import com.reuters.proto.lib.UserGrpc;
import com.reuters.proto.lib.UserInfo;
import com.reuters.proto.lib.UserInfoList;

import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;

/**
 * service implement
 * 
 * @author qingqingz
 * 
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
	@GrpcClient("local-grpc-server")
	private Channel serverChannel;

	@Override
	public Iterable<User> listAllUsers() {
		List<User> list = new ArrayList<>();
		try {
			UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(serverChannel);
			UserInfoList response = stub.find(UserInfo.newBuilder().setName("").build());
			response.getUserInfoList().forEach(user -> {
				User u = new User();
				u.setId(user.getId());
				u.setName(user.getName());
				u.setAge(user.getAge());
				list.add(u);
			});
		} catch (Exception ex) {
			LOGGER.log(Level.WARNING, ex.getMessage());
		}
		return list;
	}

	@Override
	public User getUserById(Integer id) {
		User user = new User();
		try {
			UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(serverChannel);
			UserInfoList response = stub.findById(UserInfo.newBuilder().setId(id).build());
			UserInfo ui = response.getUserInfo(0);
			user.setId(ui.getId());
			user.setName(ui.getName());
			user.setAge(ui.getAge());
		} catch (Exception ex) {
			LOGGER.log(Level.WARNING, ex.getMessage());
		}
		return user;
	}

	@Override
	public User saveUser(User user) {
		 UserInfo ui = UserInfo.newBuilder().setAge(user.getAge()).setName(user.getName()).build();
		 try {
				UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(serverChannel);
				if(null == user.getId()){
					ui = UserInfo.newBuilder().setAge(user.getAge()).setName(user.getName()).build();
					stub.add(ui);
				} else {
					ui = UserInfo.newBuilder().setId(user.getId()).setAge(user.getAge()).setName(user.getName()).build();
					stub.update(ui);
				}
			} catch (Exception ex) {
				LOGGER.log(Level.WARNING, ex.getMessage());
			}
		return user;
	}

	@Override
	public void deleteUser(Integer id) {
		try {
			UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(serverChannel);
			stub.remove(UserInfo.newBuilder().setId(id).build());
		} catch (Exception ex) {
			LOGGER.log(Level.WARNING, ex.getMessage());
		}
	}

}
