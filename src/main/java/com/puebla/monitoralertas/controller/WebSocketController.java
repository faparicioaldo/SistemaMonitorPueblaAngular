package com.puebla.monitoralertas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class WebSocketController {

//    private final SimpMessagingTemplate template;
	@Autowired
	private SimpMessagingTemplate template;

//    @Autowired
//    WebSocketController(SimpMessagingTemplate template){
//        this.template = template;
//    }

    @MessageMapping("/send/message")
    public void sendMessage(String message){
        log.info(message);
        template.convertAndSend("/topic",  message);
    }
}