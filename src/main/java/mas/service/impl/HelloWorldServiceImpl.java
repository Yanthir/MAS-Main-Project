package mas.service.impl;

import mas.request.HelloWorldRequest;
import mas.service.HelloWorldService;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldServiceImpl implements HelloWorldService {
    public String getHelloMessage(HelloWorldRequest request) {
        return "Hello world!";
    }
}
