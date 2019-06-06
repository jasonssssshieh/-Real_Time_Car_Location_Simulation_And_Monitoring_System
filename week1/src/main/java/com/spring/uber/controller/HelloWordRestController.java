package com.spring.uber.controller;

import com.spring.uber.domain.Message;
import org.springframework.web.bind.annotation.*;

//REST API
@RestController
public class HelloWordRestController {
    @RequestMapping("/")
    public String welcome(){
        return "Hello Spring Uber";
    }

    @RequestMapping(value = "/hello/{yourName}", method = RequestMethod.GET)
    public Message showMessage(@PathVariable String yourName){
        Message message = new Message(yourName, "Hello, " + yourName);
        return message;
    }
}