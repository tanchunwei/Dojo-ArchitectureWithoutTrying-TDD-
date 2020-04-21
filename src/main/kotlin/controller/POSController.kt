package com.pos.controller

import com.pos.service.POSService
import com.pos.view.WebDisplay
import model.Price
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import repository.InventoryInMemoryRepo
import java.time.LocalDateTime

@RestController()
@RequestMapping("api")
class PosController{
    private val display = WebDisplay()
    private val posService : POSService = POSService(
        InventoryInMemoryRepo(mapOf("12345" to Price(1250))),
        display
    )

    @RequestMapping(value = ["/test"], method = [RequestMethod.GET], produces = ["application/json"])
    fun test() : ResponseEntity<String>{
        return  ResponseEntity<String>("test", HttpStatus.OK)
    }

    @GetMapping("test2")
    fun test2() : ResponseEntity<String>{
        return  ResponseEntity<String>("test", HttpStatus.OK)
    }

    @GetMapping("barcode/{barcode}", produces = ["application/json"])
    fun barcode(@PathVariable barcode : String) : ResponseEntity<String>{
        println("${LocalDateTime.now()}: barcode call")
        posService.onBarcode(barcode)
        return  ResponseEntity<String>(display.toJson(), HttpStatus.OK)
    }
}