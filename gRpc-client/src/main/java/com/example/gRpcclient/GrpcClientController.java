package com.example.gRpcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reuters.proto.lib.UserInfo;

@RestController
public class GrpcClientController {

	@Autowired
	private GrpcUserClientService grpcUserClientService;


	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String findFromMem() {
		return grpcUserClientService.findFromMem();
	}
	
	

}
