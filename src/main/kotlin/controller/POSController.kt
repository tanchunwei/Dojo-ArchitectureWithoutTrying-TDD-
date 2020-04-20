package com.pos.controller

import com.pos.service.POSService
import model.Price
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import repository.InventoryInMemoryRepo
import view.ConsoleDisplay

@RestController()
@RequestMapping("api")
class PosController{
    private val posService : POSService = POSService(
        InventoryInMemoryRepo(mapOf("12345" to Price(1250))),
        ConsoleDisplay()
    )

    @RequestMapping(value = ["/test"], method = [RequestMethod.GET], produces = ["application/json"])
    fun test() : ResponseEntity<String>{
        return  ResponseEntity<String>("test", HttpStatus.OK)
    }

    @GetMapping("test2")
    fun test2() : ResponseEntity<String>{
        return  ResponseEntity<String>("test", HttpStatus.OK)
    }

    @GetMapping("barcode/{barcode}")
    fun barcode(@PathVariable barcode : String) : ResponseEntity<String>{
        posService.onBarcode(barcode)
        return  ResponseEntity<String>("test", HttpStatus.OK)
    }
}