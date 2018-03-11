package mas.controller;

import mas.model.HelloWorldModel;
import mas.request.HelloWorldRequest;
import mas.response.HelloWorldResponse;
import mas.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping("/")
    public String welcome() {
        return "Welcome!";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private HelloWorldResponse getHelloWorld(@RequestParam HelloWorldRequest request) {
        return new HelloWorldResponse(new HelloWorldModel(0, helloWorldService.getHelloMessage(request)));
    }

    @RequestMapping("/greeting")
    public String greeting() {
        return "Hello World!";
    }
}
