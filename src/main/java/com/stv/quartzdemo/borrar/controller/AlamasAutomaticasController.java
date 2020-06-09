package com.stv.quartzdemo.borrar.controller;
//package com.stv.quartzdemo.controller;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.stereotype.Controller;
//
//import com.stv.quartzdemo.dto.ChatMessage;
//
//@Controller
//public class AlamasAutomaticasController {
//
//	@MessageMapping("/chat.sendMessage")
//	@SendTo("/topic/public")
//	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//		return chatMessage;
//	}
//
//	@MessageMapping("/chat.addUser")
//	@SendTo("/topic/public")
//	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//		return chatMessage;
//	}
//
//}
