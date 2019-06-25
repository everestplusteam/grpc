package com.example.gRpcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    
    @RequestMapping("/searchAll")
    public String find() {
        return grpcUserClientService.find();
    }
    
//    @RequestMapping("/add")
//    public String add() {
//        return grpcUserClientService.add();
//    }
//    
//    @RequestMapping("/searchAll")
//    public String find() {
//        return grpcUserClientService.find();
//    }
//    
//    @RequestMapping("/searchAll")
//    public String find() {
//        return grpcUserClientService.find();
//    }
//    
//    
    
}
