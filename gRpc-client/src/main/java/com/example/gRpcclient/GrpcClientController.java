package com.example.gRpcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.devh.examples.grpc.lib.UserInfo;

@RestController
public class GrpcClientController {
	@Autowired
	private GrpcClientService grpcClientService;

	@Autowired
	private GrpcUserClientService grpcUserClientService;

	@RequestMapping("/print")
	public String printMessage(@RequestParam(defaultValue = "Michael") String name) {
		return grpcClientService.sendMessage(name);
	}

	@RequestMapping(value = "/searchAll", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String find() {
		return grpcUserClientService.find();
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String findById(@RequestParam("id") int id) {
		UserInfo user = UserInfo.newBuilder().setId(id).build();
		return grpcUserClientService.findById(user);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String add(@RequestParam("name") String name, @RequestParam("age") int age) {
		UserInfo user = UserInfo.newBuilder().setAge(age).setName(name).build();
		return grpcUserClientService.add(user);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String update(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("age") int age) {
        UserInfo user = UserInfo.newBuilder().setId(id).setAge(age).setName(name).build();
        return grpcUserClientService.update(user);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String del(@RequestParam("id") int id) {
        return grpcUserClientService.del(id);
    }

}
