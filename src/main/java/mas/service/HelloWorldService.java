package mas.service;

import mas.request.HelloWorldRequest;

public interface HelloWorldService {
    String getHelloMessage(HelloWorldRequest request);
}
