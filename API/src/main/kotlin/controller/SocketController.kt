package com.pos.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.pos.model.request.SocketTestRequest
import com.pos.model.response.SocketTestResponse
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class SocketController{
    private val objectMapper = ObjectMapper()

    @MessageMapping("/testsocket")
    @SendTo("/topic/testsocket")
    fun testing(request : String): String{
        val socketRequest = objectMapper.readValue(request, SocketTestRequest::class.java)
        println("Test: ${socketRequest.messageBy}")
        Thread.sleep(1000)
        return objectMapper.writeValueAsString(SocketTestResponse("Response by: ${socketRequest.messageBy}"))
    }

    @MessageMapping("/testidsocket/{id}")
    @SendTo("/topic/testidsocket.{id}")
    fun testingid(request : String, @DestinationVariable id : String): String{
        val socketRequest = objectMapper.readValue(request, SocketTestRequest::class.java)
        println("From: $id Test: ${socketRequest.messageBy}")
        Thread.sleep(1000)
        return objectMapper.writeValueAsString(SocketTestResponse("From $id Response by: ${socketRequest.messageBy}"))
    }
}